package com.garderie.service.validator.dto;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.types.dto.SignUpDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SignupWithCodeValidator extends SignUpDTOValidator {

    @Override
    public List<GarderieErrors> validate(final SignUpDTO signUpDTO) {

        final List<GarderieErrors> errors = new ArrayList<>();
        errors.addAll(super.validate(signUpDTO));

        if (StringUtils.isBlank(signUpDTO.getPassword())) {
            errors.add(GarderieErrors.MISSING_PASSWORD);
        }
        if (StringUtils.isBlank(signUpDTO.getConfirmPassword())) {
            errors.add(GarderieErrors.MISSING_PASSWORD);
        }
        if (!StringUtils.equals(signUpDTO.getPassword(), signUpDTO.getConfirmPassword())) {
            errors.add(GarderieErrors.PASSWORD_MISMATCH);
        }
        if (StringUtils.isBlank(signUpDTO.getSecretCode())) {
            errors.add(GarderieErrors.MISSING_SECRET_CODE);
        }

        return errors;
    }
}
