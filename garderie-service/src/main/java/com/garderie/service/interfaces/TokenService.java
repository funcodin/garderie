package com.garderie.service.interfaces;

import com.garderie.types.security.auth.UserAuthentication;

public interface TokenService {
    //String getToken(String username, String password);
    String generateJwtToken(final UserAuthentication userAuthentication);
}
