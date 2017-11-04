package com.garderie.types.user.info;

import java.util.List;

public class Parent extends User{

	private UserType userType;
	private Relation relationshipToChild;
	private List<String> childrenIds;

	public UserType getUserType() {
		return this.userType;
	}

	public void setUserType(final UserType userType) {
		this.userType = UserType.PARENT;
	}

	public Relation getRelationshipToChild() {
		return this.relationshipToChild;
	}

	public void setRelationshipToChild(final Relation relationshipToChild) {
		this.relationshipToChild = relationshipToChild;
	}
	
}
