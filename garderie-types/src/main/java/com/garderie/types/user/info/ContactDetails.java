package com.garderie.types.user.info;

import java.util.List;

public class ContactDetails{

	private List<String> cellPhoneNumber;
	private String homePhoneNumber;
	private String workPhoneNumber;
	public List<String> getCellPhoneNumber() {
		return this.cellPhoneNumber;
	}
	public void setCellPhoneNumber(final List<String> cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
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
