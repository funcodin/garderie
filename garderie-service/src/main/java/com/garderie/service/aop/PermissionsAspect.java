package com.garderie.service.aop;

import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.TokenService;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.security.consts.SecurityConsts;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

@Aspect
@Component
public class PermissionsAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(PermissionsAspect.class);

    @Autowired
    TokenService tokenService;

    @Before("@annotation(com.garderie.service.aop.PermissionsCheck)")
    public void checkSecurityPerms(JoinPoint proceedingJoinPoint) throws Throwable {


        final MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        final Method method = methodSignature.getMethod();
        final PermissionsCheck securityCheck = method.getAnnotation(PermissionsCheck.class);
        final List<ActionPermissions> actionPermissions = Arrays.asList(securityCheck.hasPermissions());
        final List<Authority> authorities = Arrays.asList(securityCheck.hasAuthority());
        final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final String token = httpServletRequest.getHeader(SecurityConsts.AUTH_HEADER_NAME);

        LOGGER.info("Expected perms {}", actionPermissions);

        final JwtTokenData jwtTokenData = this.tokenService.getJwtTokenDataFromJwtToken(token);
        httpServletRequest.setAttribute(SecurityConsts.JWT_TOKEN_DATA, jwtTokenData);


        final List<Authority> authoritiesFromToken = jwtTokenData.getAuthorities();
        boolean authPassed = false;

        for(Authority authority : authorities) {
            if(authoritiesFromToken.contains(authority.name())) {
                authPassed = true;
                break;
            }
        }

        if(authPassed){
            LOGGER.info("User has authority to perfom this operation");
        }else{
            throw new ServiceException("User unauthorized to perform this operation", HttpStatus.UNAUTHORIZED);
        }

        final List<ActionPermissions> actionPermissionsFromToken = jwtTokenData.getActionPermissions();
        boolean hasPermission = false;
        for(ActionPermissions actionPermission : actionPermissions ){
            if(actionPermissionsFromToken.contains(actionPermission.name())){
                hasPermission = true;
               break;
            }
        }

        if(hasPermission){
            LOGGER.info("User has permissions");
        }else{
            throw new ServiceException("User does not have permisssion to perform this operation", HttpStatus.FORBIDDEN);
        }
    }
}
