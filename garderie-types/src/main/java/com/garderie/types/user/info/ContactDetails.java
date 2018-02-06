package com.garderie.types.user.info;

import java.util.List;

public class ContactDetails {

	private List<String> cellPhoneNumbers;
	private String homePhoneNumber;
	private String workPhoneNumber;
	
	public List<String> getCellPhoneNumbers() {
		return this.cellPhoneNumbers;
	}
	public void setCellPhoneNumbers(final List<String> cellPhoneNumbers) {
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
