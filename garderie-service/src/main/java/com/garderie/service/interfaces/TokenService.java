package com.garderie.service.interfaces;

import com.garderie.types.security.auth.UserAuthentication;
import com.garderie.types.security.auth.permissions.ActionPermissions;

import java.util.List;

public interface TokenService {
    //String getToken(String username, String password);
    String generateJwtToken(final UserAuthentication userAuthentication);
    List<ActionPermissions> getActionPermissionFromJwtToken( final String jwtToken);

}
