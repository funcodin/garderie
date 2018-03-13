package com.garderie.service.controller.organization;


import com.garderie.service.aop.PermissionsCheck;
import com.garderie.service.interfaces.ClassroomService;
import com.garderie.types.GarderieResponse;
import com.garderie.types.org.Classroom;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/classroom")
public class ClassroomController {

    @Autowired
    private ClassroomService classroomService;

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_CLASSROOM}, hasAuthority = {Authority.ROLE_OWNER})
    @RequestMapping(method = RequestMethod.POST)
    public GarderieResponse createClassroom(@RequestBody final Classroom classroom) {
        final GarderieResponse response = new GarderieResponse();
        final Classroom createdClassroom = this.classroomService.create(classroom);
        response.addData("classrooms",this.classroomService.findAllClassroomsByOrgId(classroom.getOrgId()));
        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.UPDATE_CLASSROOM}, hasAuthority = {Authority.ROLE_OWNER})
    @RequestMapping(method = RequestMethod.PUT)
    public GarderieResponse updateClassroom(@RequestBody final Classroom classroom){
        final GarderieResponse response = new GarderieResponse();
        final Classroom updatedClassroom = this.classroomService.update(classroom);
        response.addData("classrooms",this.classroomService.findAllClassroomsByOrgId(classroom.getOrgId()));
        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_STUDENT_TO_CLASSROOM}, hasAuthority = {Authority.ROLE_OWNER,Authority.ROLE_TEACHER})
    @RequestMapping(method = RequestMethod.PUT,value = "/addstudent")
    public GarderieResponse addStudentsToClassroom(@RequestBody final Classroom classroom) {
        final GarderieResponse response = new GarderieResponse();
        final Classroom updatedClassroom = this.classroomService.addStudentsToClassroom(classroom);
        response.addData("classrooms",this.classroomService.findAllClassroomsByOrgId(classroom.getOrgId()));
        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.REMOVE_STUDENT_FROM_CLASSROOM}, hasAuthority = {Authority.ROLE_OWNER,Authority.ROLE_TEACHER})
    @RequestMapping(method = RequestMethod.PUT,value = "/removestudent")
    public GarderieResponse removeStudentsFromClassroom(@RequestBody final Classroom classroom) {
        final GarderieResponse response = new GarderieResponse();
        final Classroom updatedClassroom = this.classroomService.removeStudentsFromClassroom(classroom);
        response.addData("classrooms",this.classroomService.findAllClassroomsByOrgId(classroom.getOrgId()));
        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.GET_CLASSROOMS}, hasAuthority = {Authority.ROLE_OWNER, Authority.ROLE_TEACHER})
    @RequestMapping(method = RequestMethod.GET,value = "/{orgId}")
    public GarderieResponse findAllClassRoomsByOrgId(@PathVariable final String orgId) {
        final GarderieResponse garderieResponse = new GarderieResponse();
        garderieResponse.addData("classrooms",this.classroomService.findAllClassroomsByOrgId(orgId));
        return garderieResponse;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.DELETE_CLASSROOM}, hasAuthority = {Authority.ROLE_OWNER})
    @RequestMapping(method = RequestMethod.DELETE,value = "/{classroomId}")
    public GarderieResponse deleteClassRoomByOrgId(@PathVariable final String classroomId) {
        final GarderieResponse garderieResponse = new GarderieResponse();
        this.classroomService.deleteById(classroomId);
        //TODO fix this
        //garderieResponse.addData("classrooms",this.classroomService.findAllClassroomsByOrgId(orgId));
        return garderieResponse;
    }

}
