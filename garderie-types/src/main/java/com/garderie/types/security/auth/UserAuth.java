package com.garderie.types.security.auth;

import java.util.List;

import com.garderie.types.AbstractPersistable;

public class UserAuth extends AbstractPersistable {

	private List<Authority> authorities;
	private String saltPassword;
	private Boolean isActive;
	private Boolean isPaymentDone;
	private String userId;
	
	

}
