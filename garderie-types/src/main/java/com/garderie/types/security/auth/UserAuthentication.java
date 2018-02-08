package com.garderie.types.security.auth;

public class UserAuthentication {

    private UserAccountDetails userAccountDetails;
    private UserSalt userSalt;


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
}
