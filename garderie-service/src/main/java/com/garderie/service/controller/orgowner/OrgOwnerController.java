package com.garderie.service.controller.orgowner;

import com.garderie.service.aop.PermissionsCheck;
import com.garderie.service.interfaces.OrgOwnerService;
import com.garderie.service.util.ControllerUtil;
import com.garderie.types.GarderieResponse;
import com.garderie.types.org.OrgOwner;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.types.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/orgowner")
public class OrgOwnerController {

    @Autowired
    private OrgOwnerService orgOwnerService;


    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_OWNER})
    @RequestMapping(method = RequestMethod.POST)
    public GarderieResponse createOrgOwner(@RequestBody final OrgOwner orgOwner ){
        final GarderieResponse garderieResponse = new GarderieResponse();
        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        garderieResponse.addData("owner",this.orgOwnerService.create(orgOwner, jwtTokenData));
        return garderieResponse;
    }

    @PermissionsCheck(hasPermissions = {ActionPermissions.ADD_OWNER})
    @RequestMapping(method = RequestMethod.PUT)
    public GarderieResponse updateOrgOwner( @RequestBody final OrgOwner orgOwner) {
        final GarderieResponse response = new GarderieResponse();
        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        response.addData("orgOwner",this.orgOwnerService.update(orgOwner, jwtTokenData));
        return response;
    }

}
