package com.garderie.service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/log")
public class LoginController {


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> sayHello() {
        return new ResponseEntity<>("unsecured hello!", HttpStatus.OK);
    }

}
