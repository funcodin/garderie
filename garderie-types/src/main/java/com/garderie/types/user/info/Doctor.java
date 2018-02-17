package com.garderie.types.user.info;

public class Doctor {
	private String firstName;
	private String middleName;
	private String lastName;
	private ContactDetails contactDetails;
	private DoctorType doctorType;
	
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
	public ContactDetails getContactDetails() {
		return this.contactDetails;
	}
	public void setContactDetails(final ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}
	public DoctorType getDoctorType() {
		return this.doctorType;
	}
	public void setDoctorType(final DoctorType doctorType) {
		this.doctorType = doctorType;
	}
	
}
