package com.garderie.service.it.signup;

import com.garderie.service.dto.TokenDTO;
import com.garderie.service.interfaces.OrganisationService;
import com.garderie.service.interfaces.TokenService;
import com.garderie.service.it.BaseGarderieITTest;
import com.garderie.types.GarderieResponse;
import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.info.Address;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class SignupflowIT extends BaseGarderieITTest {


    /*
    This test intial user account
    Creates secret code
    Post using owner creds with code
    verifies owner created.
     */

    @Test
    public void testOwnerSignupFlow() throws IOException {

        final SignUpDTO signUpDTO = new SignUpDTO();
        signUpDTO.setEmailId("ittessdddtddw1@test.com");
        HttpEntity httpEntity = new HttpEntity(signUpDTO, this.createDefaultHeaders());
        ResponseEntity<GarderieResponse> responseEntity = (ResponseEntity<GarderieResponse>) this.executePostRequest("/garderie/api/signup/owner", httpEntity, GarderieResponse.class);
        GarderieResponse response = responseEntity.getBody();
        Assert.assertNotNull(response);

        final UserAccountDetails userAccountDetails = this.userAccountDetailsService.findByEmailId(signUpDTO.getEmailId());

        Assert.assertNotNull(userAccountDetails);
        Assert.assertEquals(signUpDTO.getEmailId(), userAccountDetails.getEmailId());

        signUpDTO.setSecretCode(userAccountDetails.getSecretCode());
        signUpDTO.setPassword("password");
        signUpDTO.setConfirmPassword("password");

        httpEntity = new HttpEntity(signUpDTO, this.createDefaultHeaders());
        responseEntity = (ResponseEntity<GarderieResponse>) this.executePostRequest("/garderie/api/signup/code", httpEntity, GarderieResponse.class);
        final GarderieResponse tokenresponse = responseEntity.getBody();
        final String stringresponse = this.objectMapper.writeValueAsString(tokenresponse.getData().get(0));
        final TokenDTO tokenDTO = this.objectMapper.readValue(stringresponse, TokenDTO.class);
        Assert.assertNotNull(tokenDTO);

        this.userAccountDetailsService.delete(userAccountDetails.getId());
        this.userSaltService.deleteByEmailId(userAccountDetails.getEmailId());

    }






}
