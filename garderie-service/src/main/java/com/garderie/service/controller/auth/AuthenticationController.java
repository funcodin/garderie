package com.garderie.service.controller.auth;

import com.garderie.service.impl.auth.UserAuthenticationServiceImpl;
import com.garderie.service.interfaces.UserSaltHashingService;
import com.garderie.service.interfaces.UserSaltService;
import com.garderie.types.security.auth.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.garderie.service.dto.LoginDTO;
import com.garderie.service.dto.TokenDTO;
import com.garderie.service.interfaces.TokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private UserSaltService userSaltService;

    @Autowired
    private UserSaltHashingService userSaltHashingService;

    @Autowired
    private UserAuthenticationServiceImpl userAuthenticationService;



    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody final LoginDTO dto) {

        //TODO Add validation

        final UserAuthentication userAuthentication = this.userAuthenticationService.getUserAuthenticationByEmailId(dto.getUsername());

        final String encryptedPassword = this.userSaltHashingService.generateHashWithSalt(dto.getPassword(), userAuthentication.getUserSalt().getSalt());

        if( !userAuthentication.getUserAccountDetails().getEncryptedPassword().equals(encryptedPassword)) {
            return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
        }

        final String token = this.tokenService.generateJwtToken(userAuthentication);
        if (token != null) {
            final TokenDTO response = new TokenDTO();
            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }
}