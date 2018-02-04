package com.garderie.service.aop;

import com.garderie.service.constants.SecurityConstants;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.TokenService;
import com.garderie.types.security.auth.permissions.ActionPermissions;
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
        final HttpServletRequest httpServletRequest = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
        final String token = httpServletRequest.getHeader(SecurityConstants.AUTH_HEADER_NAME);


        LOGGER.info("Expected perms {}", actionPermissions);

        List<ActionPermissions> actionPermissionsFromToken = this.tokenService.getActionPermissionFromJwtToken(token);
            boolean authPassed = false;
        for(ActionPermissions actionPermission : actionPermissions ){
            if(actionPermissionsFromToken.contains(actionPermission.name())){
               authPassed = true;
               break;
            }
        }

        if(authPassed){
            LOGGER.info("Auth passed");
        }else{
            throw new ServiceException("UnAuthorized", HttpStatus.FORBIDDEN);
        }

    }
}
