package com.garderie.service.validator.org;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.validator.Validator;
import com.garderie.types.org.OrganisationAddress;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrganisationAddressValidator implements Validator<OrganisationAddress> {
    @Override
    public List<GarderieErrors> validatePreSave(OrganisationAddress organisationAddress) {
        return null;
    }

    @Override
    public List<GarderieErrors> validatePostSave(OrganisationAddress organisationAddress) {
        return null;
    }

    @Override
    public List<GarderieErrors> validate(final OrganisationAddress organisationAddress) {
        final List<GarderieErrors> errors = new ArrayList<>();
        if(StringUtils.isBlank(organisationAddress.getStreetAddress())){
            errors.add(GarderieErrors.MISSING_ORG_STREET_NAME);
        }

        if(StringUtils.isBlank(organisationAddress.getCity())){
            errors.add(GarderieErrors.MISSING_ORG_CITY);
        }

        if(StringUtils.isBlank(organisationAddress.getState())){
            errors.add(GarderieErrors.MISSING_ORG_STATE);
        }

        if(StringUtils.isBlank(organisationAddress.getZipCode())){
            errors.add(GarderieErrors.MISSING_ORG_ZIPCODE);
        }
        if(StringUtils.isBlank(organisationAddress.getCountry())){
            errors.add(GarderieErrors.MISSING_ORG_COUNTRY);
        }
        return errors;
    }
}
