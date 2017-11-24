package com.garderie.types.user.types;

import com.garderie.types.user.info.User;
import com.garderie.types.user.info.UserType;

import java.util.List;

public class Teacher extends User {

	private UserType userType;
	private String orgId;
	private List<String> childrenIds;
	private List<String> classRoomIds;

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(final UserType userType) {
		this.userType = UserType.TEACHER;
	}
}
