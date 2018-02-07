package com.garderie.service.impl.organisation;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.impl.auth.UserAuthenticationServiceImpl;
import com.garderie.service.interfaces.OrganisationService;
import com.garderie.service.interfaces.TokenService;
import com.garderie.service.interfaces.UserPermissionsService;
import com.garderie.service.repository.organisation.OrganisationRepository;
import com.garderie.service.validator.org.OrganisationAddressValidator;
import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.permissions.UserPermissions;
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
    private UserPermissionsService userPermissionsService;

    @Autowired
    private UserAuthenticationServiceImpl userAuthenticationService;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private OrganisationAddressValidator organisationAddressValidator;


    @Override
    public Organisation create(final Organisation organisation, final JwtTokenData jwtTokenData) {


        //Get user from jwt token check if user already has organisation assigned
        final UserPermissions userPermissions = this.userPermissionsService.findByEmailId(jwtTokenData.getUserName());
        if (StringUtils.isNotBlank(userPermissions.getOrganisationId())) {
            throw new ServiceException("User cannot create more than one organisation", HttpStatus.BAD_REQUEST);
        }

        final Organisation createdOrganisation = this.organisationRepository.save(organisation);

        //set user permissions with
        userPermissions.setOrganisationId(createdOrganisation.getId());
        this.userPermissionsService.update(userPermissions);

        return createdOrganisation;
    }

    @Override
    public Organisation update(final Organisation organisation, final String orgId, final JwtTokenData jwtTokenData) {

        if (StringUtils.isBlank(orgId)) {
            throw new ServiceException("Organisation id cannot be empty for update.", HttpStatus.BAD_REQUEST);
        }

        if (!orgId.equals(jwtTokenData.getOrgId())) {
            throw new ServiceException("Organisation can be updated by org owner only", HttpStatus.BAD_REQUEST);
        }

        final Organisation existingOrganisation = this.organisationRepository.findById(orgId);

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
}
