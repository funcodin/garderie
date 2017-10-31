package com.garderie.types.security.auth;

import java.util.List;

import com.garderie.types.AbstractPersistable;

public class UserAuth extends AbstractPersistable {

	private List<Authority> authorities;
	private String saltPassword;
	private Boolean isActive;
	private Boolean isPaymentDone;
	private String userId;
	
	public List<Authority> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<Authority> authorities) {
		this.authorities = authorities;
	}
	public String getSaltPassword() {
		return saltPassword;
	}
	public void setSaltPassword(String saltPassword) {
		this.saltPassword = saltPassword;
	}
	public Boolean getIsActive() {
		return isActive;
	}
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	public Boolean getIsPaymentDone() {
		return isPaymentDone;
	}
	public void setIsPaymentDone(Boolean isPaymentDone) {
		this.isPaymentDone = isPaymentDone;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	

}
