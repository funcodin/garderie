package com.garderie.service.converter;

import com.garderie.types.security.auth.UserAuth;
import org.springframework.core.convert.converter.Converter;
import com.garderie.service.dto.UserDTO;


public class UserDTOConverter implements Converter<UserDTO, UserAuth> {

    @Override
    public UserAuth convert(final UserDTO dto) {
        final UserAuth user = new UserAuth();
        user.setUserId(dto.getUsername());
        user.setSaltPassword(dto.getPassword());
        user.setEmailId(dto.getUsername());
       return user;
    }
}
