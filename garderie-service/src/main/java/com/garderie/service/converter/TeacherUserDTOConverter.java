package com.garderie.service.converter;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;

import java.util.Arrays;

public class TeacherUserDTOConverter extends BaseUserSignUpDTOConverter {
    @Override
    public UserAccountDetails convert(SignUpDTO dto) {
        final UserAccountDetails userAccountDetails = super.convert(dto);
        userAccountDetails.setAuthorities(Arrays.asList(Authority.ROLE_TEACHER));
        return userAccountDetails;
    }
}
