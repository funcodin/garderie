package com.garderie.service.impl.organisation;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.OrgOwnerService;
import com.garderie.service.interfaces.OrganisationService;
import com.garderie.service.interfaces.TokenService;
import com.garderie.service.interfaces.UserAccountDetailsService;
import com.garderie.service.repository.organisation.OrganisationRepository;
import com.garderie.service.validator.org.OrganisationAddressValidator;
import com.garderie.types.org.OrgOwner;
import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.token.JwtTokenData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class OrganisationServiceImpl implements OrganisationService {

    @Autowired
    private OrganisationRepository organisationRepository;

    @Autowired
    private UserAccountDetailsService userAccountDetailsService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OrganisationAddressValidator organisationAddressValidator;

    @Autowired
    private OrgOwnerService orgOwnerService;


    @Override
    public Organisation create(final Organisation organisation, final JwtTokenData jwtTokenData) {


        //Get user from jwt token check if user already has organisation assigned
        final UserAccountDetails userAccountDetails = this.userAccountDetailsService.findByEmailId(jwtTokenData.getUserName());
        if (StringUtils.isNotBlank(userAccountDetails.getOrganisationId())) {
            throw new ServiceException("User cannot create more than one organisation", HttpStatus.BAD_REQUEST);
        }

        organisation.setOrgOwnerId(jwtTokenData.getUserId());
        final Organisation createdOrganisation = this.organisationRepository.save(organisation);

        //set user permissions with
        userAccountDetails.setOrganisationId(createdOrganisation.getId());
        this.userAccountDetailsService.update(userAccountDetails);


        final OrgOwner orgOwner = this.orgOwnerService.findByEmailId(userAccountDetails.getEmailId());

        if (Objects.isNull(orgOwner)) {
            throw new ServiceException("Could find owner for created organisation", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //Update org owner with org id
        orgOwner.setOrgId(createdOrganisation.getId());
        this.orgOwnerService.updateOrgOwnerOrgIdInternally(userAccountDetails.getId(), createdOrganisation.getId());

        return createdOrganisation;
    }

    @Override
    public Organisation update(final Organisation organisation, final JwtTokenData jwtTokenData) {

        if (Objects.isNull(organisation) || StringUtils.isBlank(organisation.getId())) {
            throw new ServiceException("Organisation id is required for update.", HttpStatus.BAD_REQUEST);
        }

        if (!organisation.getId().equals(jwtTokenData.getOrgId())) {
            throw new ServiceException("Organisation can be updated by org owner only", HttpStatus.BAD_REQUEST);
        }

        final Organisation existingOrganisation = this.organisationRepository.findById(organisation.getId());

        if (StringUtils.isNotBlank(organisation.getOrgName())) {
            existingOrganisation.setOrgName(organisation.getOrgName());
        }

        if (Objects.nonNull(organisation.getOrganisationAddress())) {
            final List<GarderieErrors> validationErrors = this.organisationAddressValidator.validate(organisation.getOrganisationAddress());
            if (CollectionUtils.isNotEmpty(validationErrors)) {
                throw new ServiceException("Validation Errors for address", HttpStatus.BAD_REQUEST);
            }
            existingOrganisation.setOrganisationAddress(organisation.getOrganisationAddress());
        }
        existingOrganisation.setModifiedDate(new Date());
        return this.organisationRepository.save(existingOrganisation);
    }

    @Override
    public Organisation findOrganisationById(String id) {
        return this.organisationRepository.findById(id);
    }

    @Override
    public void deletedById(String id) {
        this.organisationRepository.delete(id);
    }

    @Override
    public Organisation findOrganisationByOwnerId(String ownerId) {
        return this.organisationRepository.findByOrgOwnerId(ownerId);
    }
}
