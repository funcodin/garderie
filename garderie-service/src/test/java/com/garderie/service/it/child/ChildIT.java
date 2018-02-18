package com.garderie.service.it.child;

import com.garderie.service.dto.TokenDTO;
import com.garderie.service.interfaces.ChildService;
import com.garderie.service.it.BaseGarderieITTest;
import com.garderie.types.GarderieResponse;
import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.types.Child;
import org.apache.commons.collections4.CollectionUtils;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

public class ChildIT extends BaseGarderieITTest{

    @Autowired
    private ChildService childService;

    @Test
    public void testAddChildToOrgFlow() throws IOException{

        final TokenDTO tokenDTO = this.createOwnerToken();

        final Organisation organisation = new Organisation();
        organisation.setOrgName("Integration test day care");

        final HttpHeaders httpHeaders = this.createDefaultHeaders();
        httpHeaders.set("x-auth-token", tokenDTO.getToken());

        final HttpEntity httpEntity = new HttpEntity(organisation, httpHeaders);

        final ResponseEntity<GarderieResponse> responseEntity = (ResponseEntity<GarderieResponse>) this.executePostRequest("/garderie/organisation", httpEntity, GarderieResponse.class);
        GarderieResponse updatedTokeResponse = responseEntity.getBody();
        final String updatedJwtToken = this.objectMapper.writeValueAsString(updatedTokeResponse.getData("token"));
        final TokenDTO updatedTokenDTO = this.objectMapper.readValue(updatedJwtToken, TokenDTO.class);
        final JwtTokenData updatedJwtTokenData = this.tokenService.getJwtTokenDataFromJwtToken(updatedTokenDTO.getToken());
        Objects.nonNull(updatedJwtToken);


        Child child = new Child();
        child.setFirstName("R");
        child.setLastName("D");


        final HttpHeaders childHttpHeaders = this.createDefaultHeaders();
        childHttpHeaders.set("x-auth-token", updatedTokenDTO.getToken());
        final HttpEntity childHttpEntity = new HttpEntity(child, childHttpHeaders);


        final ResponseEntity<GarderieResponse> childResponseEntity = (ResponseEntity<GarderieResponse>) this.executePostRequest("/garderie/child", childHttpEntity, GarderieResponse.class);
        GarderieResponse childUpdatedResponse = childResponseEntity.getBody();
        final String createdChildResponse = this.objectMapper.writeValueAsString(childUpdatedResponse.getData("child"));
        final Child createdChild = this.objectMapper.readValue(createdChildResponse, Child.class);
        Objects.nonNull(createdChild);




        Set<String> parentIds = new HashSet<>();
        parentIds.add(UUID.randomUUID().toString());

        createdChild.setParentIds(parentIds);


        final HttpEntity updatedChildHttpEntity = new HttpEntity(createdChild, childHttpHeaders);

        final ResponseEntity<GarderieResponse> updatedChildResponseEntity = (ResponseEntity<GarderieResponse>) this.executePutRequest("/garderie/child", updatedChildHttpEntity, GarderieResponse.class);
        final GarderieResponse updatedChildUpdatedResponse = updatedChildResponseEntity.getBody();
        final String updatedChildResponse = this.objectMapper.writeValueAsString(updatedChildUpdatedResponse.getData("child"));
        final Child updatedChild = this.objectMapper.readValue(updatedChildResponse, Child.class);
        Objects.nonNull(updatedChild);



        this.userAccountDetailsService.delete(updatedJwtTokenData.getUserId());
        this.orgOwnerService.deleteByOrgOwnerId(updatedJwtTokenData.getUserId());
        Organisation org = this.organisationService.findOrganisationByOwnerId(updatedJwtTokenData.getUserId());
        this.organisationService.deletedById(org.getId());
        this.childService.deleteChildById(updatedChild.getId());

    }
}
