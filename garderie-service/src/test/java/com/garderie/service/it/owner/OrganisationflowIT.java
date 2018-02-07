package com.garderie.service.it.owner;

import com.garderie.service.dto.TokenDTO;
import com.garderie.service.interfaces.OrganisationService;
import com.garderie.service.it.BaseGarderieITTest;
import com.garderie.types.GarderieResponse;
import com.garderie.types.org.Organisation;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.info.Address;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public class OrganisationflowIT extends BaseGarderieITTest{


    @Autowired
    private OrganisationService organisationService;

    @Test
    public void testCreateOrganisationFlow() throws IOException {
        final TokenDTO tokenDTO = this.createOwnerToken();
        final JwtTokenData jwtTokenData = this.tokenService.getJwtTokenDataFromJwtToken(tokenDTO.getToken());

        final Organisation organisation = new Organisation();
        organisation.setOrgName("Integration test day care");

        final HttpHeaders httpHeaders = this.createDefaultHeaders();
        httpHeaders.set("x-auth-token", tokenDTO.getToken());

        final HttpEntity httpEntity = new HttpEntity(organisation, httpHeaders);

        final ResponseEntity<GarderieResponse> responseEntity = (ResponseEntity<GarderieResponse>) this.executePostRequest("/garderie/organisation", httpEntity, GarderieResponse.class);
        GarderieResponse updatedTokeResponse = responseEntity.getBody();
        final String updatedJwtToken = this.objectMapper.writeValueAsString(updatedTokeResponse.getData().get(0));
        final TokenDTO updatedTokenDTO = this.objectMapper.readValue(updatedJwtToken, TokenDTO.class);
        final JwtTokenData updatedJwtTokenData = this.tokenService.getJwtTokenDataFromJwtToken(updatedTokenDTO.getToken());

        final Organisation createdOrganistion = this.organisationService.findOrganisationById(updatedJwtTokenData.getOrgId());

        Assert.assertNotNull(createdOrganistion);
        Assert.assertEquals(updatedJwtTokenData.getOrgId(), createdOrganistion.getId());

        final Organisation updatedOrganisation = new Organisation();

        final Address orgAddress = new Address();
        orgAddress.setCity("Satara");
        orgAddress.setCountry("India");
        orgAddress.setState("Maharashtra");
        orgAddress.setStreetAddress("7 Shree Bunglow Visawa Park");
        orgAddress.setZipCode("94066");
        updatedOrganisation.setOrganisationAddress(orgAddress);



        final HttpHeaders updateHttpHeaders = this.createDefaultHeaders();
        updateHttpHeaders.set("x-auth-token", updatedTokenDTO.getToken());

        final HttpEntity updatedHttpEntity = new HttpEntity(updatedOrganisation, updateHttpHeaders);

        final ResponseEntity<GarderieResponse> updateResponseEntity = (ResponseEntity<GarderieResponse>) this.executePutRequest("/garderie/organisation/"+createdOrganistion.getId(), updatedHttpEntity, GarderieResponse.class);
        final GarderieResponse updatedOrg = updateResponseEntity.getBody();

        final String updateOrgString = this.objectMapper.writeValueAsString(updatedOrg.getData().get(0));
        final Organisation updateOrg = this.objectMapper.readValue(updateOrgString, Organisation.class);

        Assert.assertEquals(updateOrg.getOrganisationAddress().getCity(), updatedOrganisation.getOrganisationAddress().getCity());




        this.userAccountDetailsService.delete(updatedJwtTokenData.getUserId());
        this.userSaltService.deleteByEmailId(updatedJwtTokenData.getUserName());
        this.userPermissionsService.deleteByEmailId(updatedJwtTokenData.getUserName());
        this.organisationService.deletedById(updatedJwtTokenData.getOrgId());


        Assert.assertNotNull(updatedTokeResponse);
        Assert.assertNotNull(updatedJwtTokenData.getOrgId());


    }

}
