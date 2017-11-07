package com.ad.security.service.impl;

public interface TokenService {
    String getToken(String username, String password);
}
