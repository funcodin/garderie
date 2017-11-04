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
	private MedicalInformation medicalInformation;
	private List<Parent> parents;
	
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(final String middleName) {
		this.middleName = middleName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	public List<String> getParentIds() {
		return this.parentIds;
	}
	public void setParentIds(final List<String> parentIds) {
		this.parentIds = parentIds;
	}
	public String getOrgId() {
		return this.orgId;
	}
	public void setOrgId(final String orgId) {
		this.orgId = orgId;
	}
	public List<String> getClassIds() {
		return this.classIds;
	}
	public void setClassIds(final List<String> classIds) {
		this.classIds = classIds;
	}
	public List<EmergencyContact> getEmergencyContacts() {
		return this.emergencyContacts;
	}
	public void setEmergencyContacts(final List<EmergencyContact> emergencyContacts) {
		this.emergencyContacts = emergencyContacts;
	}
	public MedicalInformation getMedicalInformation() {
		return this.medicalInformation;
	}
	public void setMedicalInformation(final MedicalInformation medicalInformation) {
		this.medicalInformation = medicalInformation;
	}
	public List<Parent> getParents() {
		return this.parents;
	}
	public void setParents(final List<Parent> parents) {
		this.parents = parents;
	}
	
	
	
}
