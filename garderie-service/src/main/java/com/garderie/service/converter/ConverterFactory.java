package com.garderie.service.converter;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.garderie.service.dto.UserDTO;

@Component
public class ConverterFactory {

    private Map<Object, Converter> converters;

    public ConverterFactory() {

    }

    @PostConstruct
    public void init() {
        converters = new HashMap<>();
        converters.put(UserDTO.class, new UserDTOConverter());
    }

    public Converter getConverter(final Object type) {
        return converters.get(type);
    }
}
