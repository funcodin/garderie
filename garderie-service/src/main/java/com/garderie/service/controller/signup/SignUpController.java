package com.garderie.service.controller.signup;

import com.garderie.service.dto.TokenDTO;
import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.SignUpService;
import com.garderie.service.interfaces.TokenService;
import com.garderie.service.validator.dto.SignUpDTOValidator;
import com.garderie.service.validator.dto.SignupWithCodeValidator;
import com.garderie.types.GarderieResponse;
import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserAuthentication;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/signup")
public class SignUpController {

    @Autowired
    private SignUpDTOValidator signUpDTOValidator;

    @Autowired
    private SignupWithCodeValidator signupWithCodeValidator;

    @Autowired
    private SignUpService signUpService;

    @Autowired
    private TokenService tokenService;


    @RequestMapping(method = RequestMethod.POST, value = "/owner")
    public GarderieResponse signUpOrgOwner(@RequestBody final SignUpDTO signUpDTO) {

        final GarderieResponse response = new GarderieResponse();

        final List<GarderieErrors> validationErrors = this.signUpDTOValidator.validate(signUpDTO);

        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new ServiceException(validationErrors.toString(), HttpStatus.BAD_REQUEST);
        }

        this.signUpService.signUpOwner(signUpDTO);
        //TODO change adding singupdto to response to something else
        response.addData(signUpDTO);
        return response;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/code")
    public GarderieResponse signUpBySecretCode(@RequestBody final SignUpDTO signUpDTO) {

        final GarderieResponse response = new GarderieResponse();

        final List<GarderieErrors> validationErrors = this.signupWithCodeValidator.validate(signUpDTO);
        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new ServiceException(validationErrors.toString(),HttpStatus.BAD_REQUEST);
        }

        final UserAuthentication userAuthentication = this.signUpService.signUpUserWithCode(signUpDTO);

        final String token = this.tokenService.generateJwtToken(userAuthentication);
        final TokenDTO tokenDTO = new TokenDTO();
        tokenDTO.setToken(token);

        response.addData(tokenDTO);
        return response;
    }
}
