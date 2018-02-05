package com.garderie.service.interfaces;

import com.garderie.types.security.auth.UserAuthentication;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;

import java.util.List;

public interface TokenService {
    String generateJwtToken(final UserAuthentication userAuthentication);
    String generateJwtTokenByEmailId( final String emailId);
    List<ActionPermissions> getActionPermissionFromJwtToken( final String jwtToken);
    JwtTokenData getJwtTokenDataFromJwtToken(final String jwtToken);

}
