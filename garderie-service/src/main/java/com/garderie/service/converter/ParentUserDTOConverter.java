package com.garderie.service.converter;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;

public class ParentUserDTOConverter extends BaseUserSignUpDTOConverter{
    @Override
    public UserAccountDetails convert(SignUpDTO signUpDTO) {
        final UserAccountDetails userAccountDetails = super.convert(signUpDTO);
        userAccountDetails.setAuthorities(Arrays.asList(Authority.ROLE_PARENT));
        return userAccountDetails;
    }
}
