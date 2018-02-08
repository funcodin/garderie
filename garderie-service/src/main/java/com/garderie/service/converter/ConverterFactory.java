package com.garderie.service.converter;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class ConverterFactory {

    private Map<String, Converter> converters;

    public ConverterFactory() {

    }

    @PostConstruct
    public void init() {
        this.converters = new HashMap<>();
        this.converters.put("USER_DTO", new UserDTOConverter());
        this.converters.put("ORG_OWNER", new OrgOwnerUserDTOConverter());
        this.converters.put("PARENT", new ParentUserDTOConverter());
        this.converters.put("TEACHER", new TeacherUserDTOConverter());
    }

    public Converter getConverter(final String type) {
        return this.converters.get(type);
    }
}
