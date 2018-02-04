package com.garderie.service.converter;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserSalt;
import org.springframework.core.convert.converter.Converter;

import java.util.UUID;

public class UserSaltConverter implements Converter<SignUpDTO, UserSalt> {
    @Override
    public UserSalt convert(final SignUpDTO signUpDTO) {
        final UserSalt userSalt = new UserSalt();
        userSalt.setEmailId(signUpDTO.getEmailId());
        userSalt.setSalt(UUID.randomUUID().toString());
        return userSalt;
    }
}
