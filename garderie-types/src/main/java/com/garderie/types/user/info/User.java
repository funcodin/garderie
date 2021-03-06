package com.garderie.types.user.info;

import java.util.Date;

import com.garderie.types.AbstractPersistable;

public class User extends AbstractPersistable{

	private String emailId;
	private String firstName;
	private String middleName;
	private String lastName;
	private Address address;
	private ContactDetails contactDetails;
	private Date birthDate;
	private Gender gender;
	
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
	public Date getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
