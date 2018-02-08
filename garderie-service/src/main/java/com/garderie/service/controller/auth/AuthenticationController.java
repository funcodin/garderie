package com.garderie.service.controller.auth;

import com.garderie.service.dto.LoginDTO;
import com.garderie.service.dto.TokenDTO;
import com.garderie.service.interfaces.TokenService;
import com.garderie.service.interfaces.UserAccountDetailsService;
import com.garderie.service.interfaces.UserSaltHashingService;
import com.garderie.types.security.auth.UserAccountDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserSaltHashingService userSaltHashingService;

    @Autowired
    private UserAccountDetailsService userAccountDetailsService;


    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> authenticate(@RequestBody final LoginDTO dto) {

        //TODO Add validation

        final UserAccountDetails userAccountDetails = this.userAccountDetailsService.findByEmailId(dto.getUsername());
        final String encryptedPassword = this.userSaltHashingService.generateHashWithSalt(dto.getPassword(), userAccountDetails.getSalt());

        if (!userAccountDetails.getEncryptedPassword().equals(encryptedPassword)) {
            return new ResponseEntity<>("Invalid password", HttpStatus.BAD_REQUEST);
        }

        final String token = this.tokenService.generateJwtToken(userAccountDetails);
        if (token != null) {
            final TokenDTO response = new TokenDTO();
            response.setToken(token);
            return new ResponseEntity<>(response, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Authentication failed", HttpStatus.BAD_REQUEST);
        }
    }
}