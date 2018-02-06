package com.garderie.types.security.auth.token;

import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.permissions.ActionPermissions;

import java.util.List;

public class JwtTokenData {

    private String userId;
    private List<Authority> authorities;
    private String userName;
    private List<ActionPermissions> actionPermissions;
    private String orgId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<ActionPermissions> getActionPermissions() {
        return actionPermissions;
    }

    public void setActionPermissions(List<ActionPermissions> actionPermissions) {
        this.actionPermissions = actionPermissions;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
