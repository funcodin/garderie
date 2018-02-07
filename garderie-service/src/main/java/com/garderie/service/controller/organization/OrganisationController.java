package com.garderie.service.controller.organization;

import com.garderie.service.aop.PermissionsCheck;
import com.garderie.service.dto.TokenDTO;
import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.OrganisationService;
import com.garderie.service.interfaces.TokenService;
import com.garderie.service.util.ControllerUtil;
import com.garderie.service.validator.OrgOwnerValidator;
import com.garderie.service.validator.OrganisationValidator;
import com.garderie.service.validator.org.OrganisationAddressValidator;
import com.garderie.types.GarderieResponse;
import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.info.Address;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/organisation")
public class OrganisationController {

    @Autowired
    private OrganisationValidator organisationValidator;

    @Autowired
    private OrgOwnerValidator orgOwnerValidator;

    @Autowired
    private OrganisationAddressValidator organisationAddressValidator;

    @Autowired
    private OrganisationService organisationService;

    @Autowired
    private TokenService tokenService;

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_ORGANISATION})
    @RequestMapping(method = RequestMethod.POST)
    public GarderieResponse createOrganisation(@RequestBody final Organisation organisation) {
        final GarderieResponse response = new GarderieResponse();

        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        final List<GarderieErrors> validationErrors = this.organisationValidator.validate(organisation);

        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new ServiceException(validationErrors.toString(), HttpStatus.BAD_REQUEST);
        }

        final Organisation createdOrganisation = this.organisationService.create(organisation, jwtTokenData);
        //response.addData(createdOrganisation);


        final String updatedToken = this.tokenService.generateJwtTokenByEmailId(jwtTokenData.getUserName());
        final TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(updatedToken);
        response.addData(tokenDTO);

        if (Objects.isNull(createdOrganisation.getOrgOwner())) {
            response.setNextStep("ORG_OWNER_INFO");
            return response;
        }

        if (Objects.isNull(createdOrganisation.getOrganisationAddress())) {
            response.setNextStep("ORG_ADDRESS_INFO");
            return response;
        }

        //This is when all the date is passed
        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_ORGANISATION})
    @RequestMapping(method = RequestMethod.PUT, value = "/{orgId}")
    public GarderieResponse updateOrganisation(@PathVariable final String orgId, @RequestBody final Organisation organisation) {
        final GarderieResponse response = new GarderieResponse();
        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        final Organisation updatedOrganisation = this.organisationService.update(organisation, orgId, jwtTokenData);
        response.addData(updatedOrganisation);
        return response;
    }

}
