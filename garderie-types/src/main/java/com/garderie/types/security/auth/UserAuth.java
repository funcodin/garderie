package com.garderie.types.security.auth;

import java.util.List;

import com.garderie.types.AbstractPersistable;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAuth extends AbstractPersistable implements UserDetails{

	private String emailId;
	private List<Authority> authorities;
	private String saltPassword;
	private Boolean isActive;
	private Boolean isPaymentDone;
	private String userId;
	private String secretCode;
	private Boolean accountNonExpired;
	private Boolean accountNonLocked;
	private Boolean credentialsNonExpired;
	
	public List<Authority> getAuthorities() {
		return this.authorities;
	}

    @Override
    public String getPassword() {
        return this.saltPassword;
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }

    public void setAuthorities(final List<Authority> authorities) {
		this.authorities = authorities;
	}
	public String getSaltPassword() {
		return this.saltPassword;
	}
	public void setSaltPassword(final String saltPassword) {
		this.saltPassword = saltPassword;
	}
	public Boolean getIsPaymentDone() {
		return this.isPaymentDone;
	}
	public void setIsPaymentDone(final Boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setUserId(final String userId) {
		this.userId = userId;
	}
	public String getEmailId() {
		return this.emailId;
	}
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}
	public String getSecretCode() {
		return this.secretCode;
	}
	public void setSecretCode(final String secretCode) {
		this.secretCode = secretCode;
	}
    public Boolean getActive() {
        return isActive;
    }
    public void setActive(Boolean active) {
        isActive = active;
    }
}
