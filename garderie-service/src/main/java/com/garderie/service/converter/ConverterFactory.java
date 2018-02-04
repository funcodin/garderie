package com.garderie.service.converter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserSalt;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.garderie.service.dto.UserDTO;

@Component
public class ConverterFactory {

    private Map<String, Converter> converters;

    public ConverterFactory() {

    }

    @PostConstruct
    public void init() {
        converters = new HashMap<>();
        converters.put("USER_DTO", new UserDTOConverter());
        converters.put("ORG_OWNER", new OrgOwnerUserDTOConverter());
        converters.put("USER_SALT", new UserSaltConverter());
    }

    public Converter getConverter(final String type) {
        return converters.get(type);
    }
}
