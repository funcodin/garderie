package com.garderie.service.validator.dto;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.validator.Validator;
import com.garderie.types.dto.SignUpDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class SignUpDTOValidator implements Validator<SignUpDTO>{
    @Override
    public List<GarderieErrors> validatePreSave(SignUpDTO signUpDTO) {
        return null;
    }

    @Override
    public List<GarderieErrors> validatePostSave(SignUpDTO signUpDTO) {
        return null;
    }

    @Override
    public List<GarderieErrors> validate(final SignUpDTO signUpDTO) {

        final List<GarderieErrors> errors = new ArrayList<>();
        if(Objects.isNull( signUpDTO )){
            errors.add(GarderieErrors.BAD_SIGNUP_REQUEST);
        }
        if(StringUtils.isEmpty(signUpDTO.getEmailId())) {
            errors.add(GarderieErrors.MISSING_EMAIL);
        }

    return errors;
    }
}
