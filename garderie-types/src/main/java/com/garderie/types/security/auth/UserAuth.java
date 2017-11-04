package com.garderie.types.security.auth;

import java.util.List;

import com.garderie.types.AbstractPersistable;

public class UserAuth extends AbstractPersistable {

	private String emailId;
	private List<Authority> authorities;
	private String saltPassword;
	private Boolean isActive;
	private Boolean isPaymentDone;
	private String userId;
	private String secretCode;
	
	public List<Authority> getAuthorities() {
		return this.authorities;
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
	public Boolean getIsActive() {
		return this.isActive;
	}
	public void setIsActive(final Boolean isActive) {
		this.isActive = isActive;
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

}
