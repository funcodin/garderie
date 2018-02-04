package com.garderie.service.interfaces;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;

public interface TokenAuthenticationService {
	Authentication authenticate(HttpServletRequest request);
}
