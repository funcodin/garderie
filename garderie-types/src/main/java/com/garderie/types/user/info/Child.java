package com.garderie.types.user.info;

import java.util.List;

import com.garderie.types.AbstractPersistable;

public class Child extends AbstractPersistable{

	private String firstName;
	private String middleName;
	private String lastName;
	private List<String> parentIds;
	private String orgId;
	private List<String> classIds;
	private List<EmergencyContact> emergencyContacts;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return middleName;
	}
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public List<String> getParentIds() {
		return parentIds;
	}
	public void setParentIds(List<String> parentIds) {
		this.parentIds = parentIds;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public List<String> getClassIds() {
		return classIds;
	}
	public void setClassIds(List<String> classIds) {
		this.classIds = classIds;
	}
	public List<EmergencyContact> getEmergencyContacts() {
		return emergencyContacts;
	}
	public void setEmergencyContacts(List<EmergencyContact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	
	
}
