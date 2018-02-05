package com.garderie.service.impl.jwt;

import com.garderie.service.interfaces.TokenAuthenticationService;
import com.garderie.types.security.auth.UserRequestAuthentication;
import com.garderie.types.security.consts.SecurityConsts;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;


@Service
public class JsonWebTokenAuthenticationService implements TokenAuthenticationService {

    @Autowired
    JsonWebTokenService jsonWebTokenService;

    @Override
    public Authentication authenticate(final HttpServletRequest request) {
        final String token = request.getHeader(SecurityConsts.AUTH_HEADER_NAME);
        final Jws<Claims> tokenData = this.jsonWebTokenService.parseToken(token);
        if (tokenData != null) {
            return new UserRequestAuthentication(token);
        }
        return null;
    }
}
