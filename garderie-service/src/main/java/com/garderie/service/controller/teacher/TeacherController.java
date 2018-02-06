package com.garderie.service.controller.teacher;

import com.garderie.service.aop.PermissionsCheck;
import com.garderie.service.interfaces.TeacherService;
import com.garderie.service.util.ControllerUtil;
import com.garderie.types.GarderieResponse;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.types.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teacher")
public class TeacherController {


    @Autowired
    private TeacherService teacherService;

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_TEACHER})
    @RequestMapping(method = RequestMethod.POST)
    public GarderieResponse createTeacher(@RequestBody final Teacher teacher) {
        final GarderieResponse response = new GarderieResponse();
        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();

        response.addData(this.teacherService.create(teacher, jwtTokenData));

        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_TEACHER})
    @RequestMapping(method = RequestMethod.PUT, value = "/{teacherId}")
    public GarderieResponse updateTeacher(@PathVariable String teacherId, @RequestBody final Teacher teacher) {
        final GarderieResponse response = new GarderieResponse();
        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();

        response.addData(this.teacherService.update(teacher, teacherId, jwtTokenData));

        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_TEACHER})
    @RequestMapping(method = RequestMethod.DELETE, value = "/{teacherId}")
    public GarderieResponse deleteTeacher(@PathVariable String teacherId, @RequestBody final Teacher teacher) {
        final GarderieResponse response = new GarderieResponse();
        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();

        //TODO delete teacher
        //response.addData(this.teacherService.update(teacher, teacherId));

        return response;
    }
}
