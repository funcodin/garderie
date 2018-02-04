package com.garderie.service.validator;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.types.org.OrgOwner;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrgOwnerValidator implements Validator<OrgOwner> {
    @Override
    public List<GarderieErrors> validatePreSave(final OrgOwner orgOwner) {
        return null;
    }

    @Override
    public List<GarderieErrors> validatePostSave(OrgOwner orgOwner) {
        return null;
    }

    @Override
    public List<GarderieErrors> validate(OrgOwner orgOwner) {
        List<GarderieErrors> errors = new ArrayList<>();
        if(StringUtils.isBlank(orgOwner.getFirstName())) {
            errors.add(GarderieErrors.ORG_OWNER_FNAME_MISSING);
        }

        if(StringUtils.isBlank(orgOwner.getLastName())) {
            errors.add(GarderieErrors.ORG_OWNER_LNAME_MISSING);
        }
        return errors;
    }
}
