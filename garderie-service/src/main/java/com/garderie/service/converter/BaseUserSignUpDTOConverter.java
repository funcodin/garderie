package com.garderie.service.converter;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserAccountDetails;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Date;

public abstract class BaseUserSignUpDTOConverter implements Converter<SignUpDTO, UserAccountDetails> {


    @Override
    public UserAccountDetails convert(final SignUpDTO dto) {
        final UserAccountDetails user = new UserAccountDetails();
        user.setEmailId(dto.getEmailId());
        user.setEncryptedPassword(dto.getPassword());
        user.setActive(false);
        user.setSecretCode(RandomStringUtils.randomAlphabetic(5));
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setAccountExpirationDate(DateUtils.addMonths(new Date(), 1));

        return user;
    }

}
