package com.garderie.service.validator;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.types.security.auth.permissions.UserPermissions;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserPermissionsValidator extends AbstractValidator implements Validator<UserPermissions> {
    @Override
    public List<GarderieErrors> validatePreSave(UserPermissions userPermissions) {
        final List<GarderieErrors> errors = new ArrayList<>();
        this.setDefaultValues(userPermissions);
        if(StringUtils.isBlank( userPermissions.getEmailId())) {
            errors.add(GarderieErrors.MISSING_EMAIL);
        }
        if(CollectionUtils.isEmpty(userPermissions.getActionPermissions())){
            errors.add(GarderieErrors.ACTION_PERMISSIONS);
        }
        return errors;
    }

    @Override
    public List<GarderieErrors> validatePostSave(UserPermissions userPermissions) {
        return null;
    }

    @Override
    public List<GarderieErrors> validate(UserPermissions userPermissions) {
        return null;
    }
}
