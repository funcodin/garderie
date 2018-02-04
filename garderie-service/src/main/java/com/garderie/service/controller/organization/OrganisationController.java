package com.garderie.service.controller.organization;

import com.garderie.service.aop.PermissionsCheck;
import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.OrganisationService;
import com.garderie.service.validator.OrgOwnerValidator;
import com.garderie.service.validator.OrganisationValidator;
import com.garderie.service.validator.org.OrganisationAddressValidator;
import com.garderie.types.GarderieResponse;
import com.garderie.types.org.OrgOwner;
import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.org.OrganisationAddress;
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

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_ORGANISATION})
    @RequestMapping(method = RequestMethod.POST)
    public GarderieResponse createOrganisation(@RequestBody final Organisation organisation) {
        final GarderieResponse response = new GarderieResponse();


        final List<GarderieErrors> validationErrors = this.organisationValidator.validate(organisation);

        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new ServiceException(validationErrors.toString(), HttpStatus.BAD_REQUEST);
        }

        final Organisation createdOrganisation = this.organisationService.create(organisation);
        response.addData(createdOrganisation);
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
    @RequestMapping(method = RequestMethod.POST, value = "/owner/{orgId}")
    public GarderieResponse addOwnerDetails(@PathVariable String orgId, @RequestBody final OrgOwner orgOwner) {
        final GarderieResponse response = new GarderieResponse();

        final List<GarderieErrors> errors = this.orgOwnerValidator.validate(orgOwner);
        if (CollectionUtils.isNotEmpty(errors)) {
            throw new ServiceException(errors.toString(), HttpStatus.BAD_REQUEST);
        }


        final Organisation organisation = this.organisationService.findOrganisationById(orgId);
        organisation.setOrgOwner(orgOwner);

        final Organisation updatedOrganisation = this.organisationService.update(organisation);
        response.addData(updatedOrganisation);

        if (Objects.isNull(updatedOrganisation.getOrganisationAddress())) {
            response.setNextStep("ORG_ADDRESS_INFO");
            return response;
        }
        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_ORGANISATION})
    @RequestMapping(method = RequestMethod.POST, value = "/address/{orgId}")
    public GarderieResponse addAddress(@PathVariable final String orgId, @RequestBody final OrganisationAddress organisationAddress) {
        final GarderieResponse response = new GarderieResponse();

        final List<GarderieErrors> validationErrors = this.organisationAddressValidator.validate(organisationAddress);

        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new ServiceException(validationErrors.toString(), HttpStatus.BAD_REQUEST);
        }

        final Organisation organisation = this.organisationService.findOrganisationById(orgId);
        organisation.setOrganisationAddress(organisationAddress);

        final Organisation createdOrganisation = this.organisationService.update(organisation);
        response.addData(createdOrganisation);
        return response;
    }

}
