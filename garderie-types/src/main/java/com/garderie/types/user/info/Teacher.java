package com.garderie.types.user.info;

import java.util.List;

public class Teacher extends User{

	private UserType userType;
	private String orgId;
	private List<String> childrenIds;

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(final UserType userType) {
		this.userType = UserType.TEACHER;
	}
}
