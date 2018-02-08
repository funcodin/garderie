package com.garderie.service.it;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.garderie.service.GarderieApplication;
import com.garderie.service.dto.TokenDTO;
import com.garderie.service.interfaces.*;
import com.garderie.types.GarderieResponse;
import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserAccountDetails;
import org.junit.Assert;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarderieApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseGarderieITTest {

    @LocalServerPort
    private int port;

    @Autowired
    public UserAccountDetailsService userAccountDetailsService;

    @Autowired
    public TokenService tokenService;

    @Autowired
    public OrgOwnerService orgOwnerService;


    private TestRestTemplate restTemplate = new TestRestTemplate();

    public ObjectMapper objectMapper = new ObjectMapper();

    public ResponseEntity<?> executeGetRequest(final String endpoint, final HttpHeaders httpHeaders, final Class responseClass) {
        HttpEntity<?> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<?> response = restTemplate.exchange(
                createURLWithPort(endpoint),
                HttpMethod.GET, entity, responseClass);
        return response;

    }


    public ResponseEntity<?> executePostRequest(final String endpoint, final HttpEntity httpEntity, final Class responseCLass) {

        final ResponseEntity<?> response = restTemplate.exchange(
                createURLWithPort(endpoint),
                HttpMethod.POST, httpEntity, responseCLass);

        return response;

    }


    public ResponseEntity<?> executePutRequest(final String endpoint, final HttpEntity httpEntity, final Class responseCLass) {

        final ResponseEntity<?> response = restTemplate.exchange(
                createURLWithPort(endpoint),
                HttpMethod.PUT, httpEntity, responseCLass);

        return response;

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

    public HttpHeaders createDefaultHeaders() {
        final HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        return httpHeaders;
    }


    public TokenDTO createOwnerToken() throws IOException {

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
        GarderieResponse tokenresponse = responseEntity.getBody();
        String stringresponse = this.objectMapper.writeValueAsString(tokenresponse.getData("token"));
        TokenDTO tokenDTO = this.objectMapper.readValue(stringresponse, TokenDTO.class);
        return tokenDTO;


    }

}
