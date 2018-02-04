package com.garderie.types.dto;

public class SignUpDTO {

    private String emailId;
    private String password;
    private String confirmPassword;
    private String secretCode;

    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return this.confirmPassword;
    }

    public void setConfirmPassword(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getSecretCode() {
        return this.secretCode;
    }

    public void setSecretCode(final String secretCode) {
        this.secretCode = secretCode;
    }
}
