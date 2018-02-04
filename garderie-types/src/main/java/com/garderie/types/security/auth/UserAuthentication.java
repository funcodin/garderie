package com.garderie.types.security.auth;

import com.garderie.types.security.auth.permissions.UserPermissions;

public class UserAuthentication {

    private UserAccountDetails userAccountDetails;
    private UserSalt userSalt;
    private UserPermissions userPermissions;


    public UserAccountDetails getUserAccountDetails() {
        return this.userAccountDetails;
    }

    public void setUserAccountDetails(final UserAccountDetails userAccountDetails) {
        this.userAccountDetails = userAccountDetails;
    }

    public UserSalt getUserSalt() {
        return this.userSalt;
    }

    public void setUserSalt(final UserSalt userSalt) {
        this.userSalt = userSalt;
    }

    public UserPermissions getUserPermissions() {
        return this.userPermissions;
    }

    public void setUserPermissions(final UserPermissions userPermissions) {
        this.userPermissions = userPermissions;
    }
}
