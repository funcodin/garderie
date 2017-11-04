package com.garderie.types.user.info;

import com.garderie.types.AbstractPersistable;

public class EmergencyContact extends AbstractPersistable{

	private String cellPhone;
	private String workPhone;
	private String homePhone;
	private String firstName;
	private String middleName;
	private String lastName;
	private Relation relationshipToChild;
	
	public String getCellPhone() {
		return this.cellPhone;
	}
	public void setCellPhone(final String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getWorkPhone() {
		return this.workPhone;
	}
	public void setWorkPhone(final String workPhone) {
		this.workPhone = workPhone;
	}
	public String getHomePhone() {
		return this.homePhone;
	}
	public void setHomePhone(final String homePhone) {
		this.homePhone = homePhone;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	public Relation getRelationshipToChild() {
		return relationshipToChild;
	}
	public void setRelationshipToChild(Relation relationshipToChild) {
		this.relationshipToChild = relationshipToChild;
	}
	
}
