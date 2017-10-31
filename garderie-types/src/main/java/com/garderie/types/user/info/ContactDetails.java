package com.garderie.types.user.info;

import java.util.List;

public class ContactDetails{

	private List<String> cellPhoneNumber;
	private String homePhoneNumber;
	private String workPhoneNumber;
	public List<String> getCellPhoneNumber() {
		return cellPhoneNumber;
	}
	public void setCellPhoneNumber(List<String> cellPhoneNumber) {
		this.cellPhoneNumber = cellPhoneNumber;
	}
	public String getHomePhoneNumber() {
		return homePhoneNumber;
	}
	public void setHomePhoneNumber(String homePhoneNumber) {
		this.homePhoneNumber = homePhoneNumber;
	}
	public String getWorkPhoneNumber() {
		return workPhoneNumber;
	}
	public void setWorkPhoneNumber(String workPhoneNumber) {
		this.workPhoneNumber = workPhoneNumber;
	}
	
	
	
}
