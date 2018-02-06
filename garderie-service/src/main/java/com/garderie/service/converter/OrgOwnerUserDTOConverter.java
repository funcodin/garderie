package com.garderie.service.converter;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;
import java.util.Date;

public class OrgOwnerUserDTOConverter extends BaseUserSignUpDTOConverter {

    @Override
    public UserAccountDetails convert(final SignUpDTO dto) {
        final UserAccountDetails userAccountDetails = super.convert(dto);
        userAccountDetails.setAuthorities(Arrays.asList(Authority.ROLE_OWNER));
        return userAccountDetails;
    }
}
