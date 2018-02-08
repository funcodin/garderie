package com.garderie.types.security.auth;

public class UserAuthentication {

    private UserAccountDetails userAccountDetails;


    public UserAccountDetails getUserAccountDetails() {
        return this.userAccountDetails;
    }

    public void setUserAccountDetails(final UserAccountDetails userAccountDetails) {
        this.userAccountDetails = userAccountDetails;
    }
}
