package com.garderie.service.it;

import com.garderie.service.GarderieApplication;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GarderieApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public abstract class BaseGarderieITTest {

    @LocalServerPort
    private int port;

    private TestRestTemplate restTemplate = new TestRestTemplate();

    public ResponseEntity<?> executeGetRequest(final String endpoint, final HttpHeaders httpHeaders, final Class responseClass) {
        HttpEntity<?> entity = new HttpEntity<>(null, httpHeaders);
        ResponseEntity<?> response = restTemplate.exchange(
                createURLWithPort(endpoint),
                HttpMethod.GET, entity, responseClass);
        return  response;

    }


    public ResponseEntity<?>  executePostRequest(final String endpoint, final HttpEntity httpEntity, final Class responseCLass) {

        final ResponseEntity<?> response = restTemplate.exchange(
                createURLWithPort(endpoint),
                HttpMethod.POST, httpEntity, responseCLass);

        return response;

    }

    private String createURLWithPort(String uri) {
        return "http://localhost:" + port + uri;
    }

}
