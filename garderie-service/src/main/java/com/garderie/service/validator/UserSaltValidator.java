package com.garderie.service.validator;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.types.security.auth.UserSalt;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserSaltValidator extends AbstractValidator implements Validator<UserSalt>{
    @Override
    public List<GarderieErrors> validatePreSave(final UserSalt userSalt) {
        final List<GarderieErrors> errors = new ArrayList<>();
        this.setDefaultValues(userSalt);
        if(StringUtils.isBlank(userSalt.getEmailId())) {
            errors.add(GarderieErrors.MISSING_EMAIL);
        }
        if(StringUtils.isBlank(userSalt.getSalt())) {
            errors.add(GarderieErrors.MISSING_USER_SALT);
        }
        return errors;
    }

    @Override
    public List<GarderieErrors> validatePostSave(final UserSalt userSalt) {

        return null;
    }

    @Override
    public List<GarderieErrors> validate(UserSalt userSalt) {
        return null;
    }
}
