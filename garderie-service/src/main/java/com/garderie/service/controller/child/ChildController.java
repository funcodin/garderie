package com.garderie.service.controller.child;

import com.garderie.service.aop.PermissionsCheck;
import com.garderie.service.interfaces.ChildService;
import com.garderie.types.GarderieResponse;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.user.types.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/child")
public class ChildController {

    @Autowired
    private ChildService childService;

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_CHILD}, hasAuthority = {Authority.ROLE_OWNER})
    @RequestMapping(method = RequestMethod.POST)
    public  GarderieResponse register(@RequestBody final Child child){
        GarderieResponse garderieResponse = new GarderieResponse();
        garderieResponse.addData("child", this.childService.create(child));
        return garderieResponse;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.UPDATE_CHILD}, hasAuthority = {Authority.ROLE_OWNER})
    @RequestMapping(method = RequestMethod.PUT)
    public GarderieResponse updateChild(@RequestBody final Child child) {
        final GarderieResponse response = new GarderieResponse();
        response.addData("child", this.childService.update(child));
        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.UPDATE_CHILD}, hasAuthority = {Authority.ROLE_OWNER})
    @RequestMapping(method = RequestMethod.DELETE, value = "/{childId}")
    public void deleteChildById(@PathVariable final String childId) {
        this.childService.deleteChildById(childId);
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.GET_ALL_CHILDRENS}, hasAuthority = {Authority.ROLE_OWNER,Authority.ROLE_TEACHER})
    @RequestMapping(method = RequestMethod.GET, value = "/{classroomId}")
    public GarderieResponse getAllChildrensInClassrooom(@PathVariable final String classroomId) {
        final GarderieResponse response = new GarderieResponse();
        response.addData("childrens",this.childService.getAllChildrensInClassroom(classroomId));
        return response;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.GET_ALL_CHILDRENS}, hasAuthority = {Authority.ROLE_OWNER,Authority.ROLE_TEACHER})
    @RequestMapping(method = RequestMethod.GET, value = "/{orgId}")
    public GarderieResponse getAllChildrensInOrg(@PathVariable final String orgId) {
        final GarderieResponse response = new GarderieResponse();
        response.addData("childrens", this.childService.getAllChildrensInOrganisation(orgId));
        return response;
    }

}
