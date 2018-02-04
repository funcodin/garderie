package com.garderie.service.impl.auth;

import com.garderie.service.impl.account.UserAccountDetailsServiceImpl;
import com.garderie.service.interfaces.UserPermissionsService;
import com.garderie.service.interfaces.UserSaltService;
import com.garderie.types.security.auth.UserAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAuthenticationServiceImpl {

    @Autowired
    UserSaltService userSaltService;

    @Autowired
    UserPermissionsService userPermissionsService;

    @Autowired
    UserAccountDetailsServiceImpl basicUserService;


    public UserAuthentication getUserAuthenticationByEmailId(final String emailId ) {
        final UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUserAccountDetails( this.basicUserService.findByEmailId(emailId));
        userAuthentication.setUserPermissions(this.userPermissionsService.findByEmailId(emailId));
        userAuthentication.setUserSalt(this.userSaltService.findByEmailId(emailId));
        return userAuthentication;
    }



}
