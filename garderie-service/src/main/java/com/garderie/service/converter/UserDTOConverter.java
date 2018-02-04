package com.garderie.service.converter;

import com.garderie.types.security.auth.UserAccountDetails;
import org.springframework.core.convert.converter.Converter;
import com.garderie.service.dto.UserDTO;


public class UserDTOConverter implements Converter<UserDTO, UserAccountDetails> {

    @Override
    public UserAccountDetails convert(final UserDTO dto) {
        final UserAccountDetails user = new UserAccountDetails();
        user.setEmailId(dto.getEmailId());
        user.setEncryptedPassword(dto.getPassword());
       return user;
    }
}
