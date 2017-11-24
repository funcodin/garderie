package com.garderie.types.security.auth;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
    ROLE_ADMIN,
    ANONYMOUS,
	ROLE_PARENT,
	ROLE_TEACHER;

    @Override
    public String getAuthority() {
        return this.name();
    }
}