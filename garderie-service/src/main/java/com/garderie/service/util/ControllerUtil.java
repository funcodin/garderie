package com.garderie.service.util;

import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.security.consts.SecurityConsts;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ControllerUtil {

    public static JwtTokenData getTokenDataFromHttpRequest( ){
        final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final JwtTokenData jwtTokenData = (JwtTokenData) httpServletRequest.getAttribute(SecurityConsts.JWT_TOKEN_DATA);
        return jwtTokenData;
    }
}
