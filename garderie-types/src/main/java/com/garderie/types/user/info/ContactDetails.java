package com.garderie.types.user.info;

import java.util.List;
import java.util.Set;

public class ContactDetails {

	private Set<String> cellPhoneNumbers;
	private String homePhoneNumber;
	private String workPhoneNumber;
	
	public Set<String> getCellPhoneNumbers() {
		return this.cellPhoneNumbers;
	}
	public void setCellPhoneNumbers(final Set<String> cellPhoneNumbers) {
		this.cellPhoneNumbers = cellPhoneNumbers;
	}
	public String getHomePhoneNumber() {
		return this.homePhoneNumber;
	}
	public void setHomePhoneNumber(final String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}
	public String getWorkPhoneNumber() {
		return this.workPhoneNumber;
	}
	public void setWorkPhoneNumber(final String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}
	
}
