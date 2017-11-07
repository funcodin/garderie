package com.garderie.service.converter;

import com.garderie.service.dto.UserDTO;
import com.garderie.types.security.auth.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class ConverterFacade {

    @Autowired
    private ConverterFactory converterFactory;

    public UserAuth convert(final UserDTO dto) {
        return (UserAuth) converterFactory.getConverter(dto.getClass()).convert(dto);
    }
}
