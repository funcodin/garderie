package com.garderie.types.user.info;

import com.garderie.types.AbstractPersistable;

public class User extends AbstractPersistable{

	private String emailId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Address address;
	private ContactDetails contactDetails;
	private UserType userType;
	private String secretCode;
	
	public String getEmailId() {
		return this.emailId;
	}
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}
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
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(final Address address) {
		this.address = address;
	}
	public ContactDetails getContactDetails() {
		return this.contactDetails;
	}
	public void setContactDetails(final ContactDetails contactDetails) {
		this.contactDetails = contactDetails;
	}
	public UserType getUserType() {
		return this.userType;
	}
	public void setUserType(final UserType userType) {
		this.userType = userType;
	}
	public String getSecretCode() {
		return this.secretCode;
	}
	public void setSecretCode(final String secretCode) {
		this.secretCode = secretCode;
	}
	
}
