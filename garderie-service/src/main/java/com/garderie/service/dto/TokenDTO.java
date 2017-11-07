package com.garderie.service.dto;

import java.io.Serializable;

public class TokenDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String token;

    public TokenDTO() {
    }

    public String getToken() {
        return token;
    }

    public void setToken(final String token) {
        this.token = token;
    }
}
