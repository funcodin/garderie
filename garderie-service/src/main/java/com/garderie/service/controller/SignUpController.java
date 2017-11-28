package com.garderie.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.garderie.service.converter.ConverterFacade;
import com.garderie.service.dto.UserDTO;
import com.garderie.service.interfaces.UserService;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    private final UserService service;

    private final ConverterFacade converterFacade;

    @Autowired
    public SignUpController(final UserService service,
                            final ConverterFacade converterFacade) {
        this.service = service;
        this.converterFacade = converterFacade;
    }

    @RequestMapping(method = RequestMethod.POST)
    public UserDTO signUp(@RequestBody final UserDTO dto) {
        service.create(converterFacade.convert(dto));
         dto.setPassword("entry created");
         return dto;
    }
}
