package com.garderie.service.it;

import com.garderie.service.dto.LoginDTO;
import org.eclipse.jetty.client.HttpResponse;
import org.junit.Test;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

public class BasicTests extends BaseGarderieITTest {


    @Test
    public void testGetMethod(){
        HttpHeaders httpHeaders = new HttpHeaders();
        ResponseEntity<String> response = (ResponseEntity<String>) this.executeGetRequest("/garderie/api/log", httpHeaders, String.class);
        System.out.println( "*****"+response.getBody());
    }

    @Test
    public void testPostMethod(){
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.setUsername("rvd@gmail.com");
        loginDTO.setPassword("password");
        HttpEntity httpEntity = new HttpEntity(loginDTO, this.createDefaultHeaders());
        ResponseEntity<String> response = (ResponseEntity<String>) this.executePostRequest("/garderie/api/auth", httpEntity, String.class);
        System.out.println( "*****"+response.getBody());


    }


}
