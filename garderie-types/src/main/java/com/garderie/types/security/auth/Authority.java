package com.garderie.types.security.auth;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {
	ROLE_PARENT,
	ROLE_TEACHER,
    ROLE_OWNER,
    ROLE_UNKNOWN;

    @Override
    public String getAuthority() {
        return this.name();
    }
}