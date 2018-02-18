package com.garderie.service.it.organisation;

import com.fasterxml.jackson.core.type.TypeReference;
import com.garderie.service.dto.TokenDTO;
import com.garderie.service.interfaces.ChildService;
import com.garderie.service.interfaces.ClassroomService;
import com.garderie.service.it.BaseGarderieITTest;
import com.garderie.types.GarderieResponse;
import com.garderie.types.org.Classroom;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.types.Child;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.Set;

public class ClassroomIT extends BaseGarderieITTest {


    @Autowired
    public ChildService childService;

    @Autowired
    public ClassroomService classroomService;


    @Test
    public void testAddClassroom() throws IOException {

        TokenDTO orgOwnerTokenDTO = this.createOwnerToken();
        orgOwnerTokenDTO = this.createOrganisation(orgOwnerTokenDTO);

        Classroom classroom = new Classroom();
        classroom.setName("Rohit Classroom");

        final HttpEntity httpEntity = this.createUserHttpEntity(orgOwnerTokenDTO.getToken(), classroom);
        final ResponseEntity<GarderieResponse> responseEntity = (ResponseEntity<GarderieResponse>) this.executePostRequest("/garderie/classroom", httpEntity, GarderieResponse.class);
        GarderieResponse response = responseEntity.getBody();
        String classrooms = this.objectMapper.writeValueAsString(response.getData("classrooms"));
        Set<Classroom> classroomSet = this.objectMapper.readValue(classrooms, new TypeReference<Set<Classroom>>() {
        });

        Assert.assertNotNull(classroomSet);

        this.deleteClassrooms(classroomSet);
        final JwtTokenData orgOwnerJwtTokenData = this.tokenService.getJwtTokenDataFromJwtToken(orgOwnerTokenDTO.getToken());
        this.userAccountDetailsService.delete(orgOwnerJwtTokenData.getUserId());
        this.orgOwnerService.deleteByOrgOwnerId(orgOwnerJwtTokenData.getUserId());
        this.organisationService.deletedById(orgOwnerJwtTokenData.getOrgId());
    }


    @Test
    public void testAddStudentsToClassRoom() throws IOException {

        TokenDTO orgOwnerTokenDTO = this.createOwnerToken();
        orgOwnerTokenDTO = this.createOrganisation(orgOwnerTokenDTO);

        // CREATE CLASSROOM
        Classroom classroom = new Classroom();
        classroom.setName("Rohit Classroom");
        final HttpEntity classroomHttpEntity = this.createUserHttpEntity(orgOwnerTokenDTO.getToken(), classroom);
        final ResponseEntity<GarderieResponse> classroomResponseEntity = (ResponseEntity<GarderieResponse>) this.executePostRequest("/garderie/classroom", classroomHttpEntity, GarderieResponse.class);
        GarderieResponse response = classroomResponseEntity.getBody();
        String classrooms = this.objectMapper.writeValueAsString(response.getData("classrooms"));
        Set<Classroom> classroomSet = this.objectMapper.readValue(classrooms, new TypeReference<Set<Classroom>>() {
        });

        Assert.assertNotNull(classroomSet);

        //CREATE CHILD
        Child child = new Child();
        child.setFirstName("Rohit");
        child.setLastName("Dhumal");

        final HttpEntity childHttpEntity = this.createUserHttpEntity(orgOwnerTokenDTO.getToken(), child);
        final ResponseEntity<GarderieResponse> childResponseEntity = (ResponseEntity<GarderieResponse>) this.executePostRequest("/garderie/child", childHttpEntity, GarderieResponse.class);
        GarderieResponse childCreatedResponse = childResponseEntity.getBody();
        final String createdChildResponse = this.objectMapper.writeValueAsString(childCreatedResponse.getData("child"));
        final Child createdChild = this.objectMapper.readValue(createdChildResponse, Child.class);
        Assert.assertNotNull(createdChild);

        //ADD CHILD TO CLASSROOM

        Classroom addChildClassroom = classroomSet.iterator().next();
        addChildClassroom.addStudentId(createdChild.getId());

        final HttpEntity addChildClassroomHttpEntity = this.createUserHttpEntity(orgOwnerTokenDTO.getToken(), addChildClassroom);
        final ResponseEntity<GarderieResponse> addChildClassroomResponseEntity = (ResponseEntity<GarderieResponse>) this.executePutRequest("/garderie/classroom/addstudent", addChildClassroomHttpEntity, GarderieResponse.class);
        GarderieResponse updatedClassroomResponse = addChildClassroomResponseEntity .getBody();
        String updatedclassrooms = this.objectMapper.writeValueAsString(updatedClassroomResponse.getData("classrooms"));
        Set<Classroom> updatedClassroomSet = this.objectMapper.readValue(updatedclassrooms , new TypeReference<Set<Classroom>>() {
        });

        Assert.assertNotNull(updatedClassroomSet);
        this.deleteClassrooms(updatedClassroomSet);

        final JwtTokenData orgOwnerJwtTokenData = this.tokenService.getJwtTokenDataFromJwtToken(orgOwnerTokenDTO.getToken());
        this.userAccountDetailsService.delete(orgOwnerJwtTokenData.getUserId());
        this.orgOwnerService.deleteByOrgOwnerId(orgOwnerJwtTokenData.getUserId());
        this.organisationService.deletedById(orgOwnerJwtTokenData.getOrgId());
        this.childService.deleteChildById(createdChild.getId());
    }


    private void deleteClassrooms( final Set<Classroom> classrooms) {
        for(final Classroom classroom : classrooms ){
            this.classroomService.deleteById(classroom.getId());
        }
    }

}
