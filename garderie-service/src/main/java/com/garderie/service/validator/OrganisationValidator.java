package com.garderie.service.validator;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.types.org.Organisation;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class OrganisationValidator extends AbstractValidator implements  Validator<Organisation>{
    @Override
    public List<GarderieErrors> validatePreSave(Organisation organisation) {
       return null;
    }

    @Override
    public List<GarderieErrors> validatePostSave(Organisation organisation) {
        return null;
    }

    @Override
    public List<GarderieErrors> validate(Organisation organisation) {
        final List<GarderieErrors> errors = new ArrayList<>();
        if(Objects.isNull(organisation)){
            errors.add(GarderieErrors.MISSING_ORGANISATION);
        }
        if(StringUtils.isBlank(organisation.getOrgName())){
            errors.add(GarderieErrors.ORG_NAME_MISSING);
        }
        this.setDefaultValues(organisation);
        return errors;
    }
}
