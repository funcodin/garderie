package com.garderie.service.aop;

import com.garderie.types.security.auth.permissions.ActionPermissions;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface PermissionsCheck {
    ActionPermissions[] hasPermissions();
}
