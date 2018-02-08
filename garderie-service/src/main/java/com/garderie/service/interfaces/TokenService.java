package com.garderie.service.interfaces;

import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;

import java.util.List;

public interface TokenService {
    String generateJwtToken(final UserAccountDetails userAccountDetails);
    String generateJwtTokenByEmailId( final String emailId);
    JwtTokenData getJwtTokenDataFromJwtToken(final String jwtToken);

}
