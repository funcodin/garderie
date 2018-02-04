package com.garderie.types.security.auth;

import com.garderie.types.AbstractPersistable;

public class UserSalt extends AbstractPersistable{

    private String emailId;
    private String salt;

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }
}
