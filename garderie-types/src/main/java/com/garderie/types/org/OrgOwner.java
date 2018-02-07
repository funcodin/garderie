package com.garderie.types.org;

import com.garderie.types.user.info.User;
import com.garderie.types.user.info.UserType;

public class OrgOwner extends User{
    private String orgId;
    private UserType userType;

    public String getOrgId() {
        return this.orgId;
    }

    public void setOrgId(final String orgId) {
        this.orgId = orgId;
    }

    public UserType getUserType() {
        return this.userType;
    }

    public void setUserType(final UserType userType) {
        this.userType = userType;
    }

}
