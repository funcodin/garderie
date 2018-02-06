package com.garderie.service.impl.organisation;

import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.impl.auth.UserAuthenticationServiceImpl;
import com.garderie.service.interfaces.OrganisationService;
import com.garderie.service.interfaces.TokenService;
import com.garderie.service.interfaces.UserPermissionsService;
import com.garderie.service.repository.organisation.OrganisationRepository;
import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.permissions.UserPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class OrganisationServiceImpl implements OrganisationService{

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


    @Override
    public Organisation create(final Organisation organisation, final JwtTokenData jwtTokenData) {


        //Get user from jwt token check if user already has organisation assigned
        final UserPermissions userPermissions = this.userPermissionsService.findByEmailId(jwtTokenData.getUserName());
        if(StringUtils.isNotBlank(userPermissions.getOrganisationId())) {
            throw new ServiceException("User cannot create more than one organisation", HttpStatus.BAD_REQUEST);
        }

        final Organisation createdOrganisation = this.organisationRepository.save(organisation);

        //set user permissions with
        userPermissions.setOrganisationId(createdOrganisation.getId());
        this.userPermissionsService.update(userPermissions);

        return createdOrganisation;
    }

    @Override
    public Organisation update(Organisation organisation) {
        return this.organisationRepository.save(organisation);
    }

    @Override
    public Organisation findOrganisationById(String id) {
        return this.organisationRepository.findById(id);
    }
}
