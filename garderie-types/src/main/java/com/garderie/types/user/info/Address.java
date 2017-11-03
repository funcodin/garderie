package com.garderie.types.user.info;

public class Address {

	private String streetAddress;
	private String unitType;
	private String state;
	private String zipCode;
	private String country;
	
	public String getStreetAddress() {
		return this.streetAddress;
	}
	public void setStreet(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getUnitType() {
		return this.unitType;
	}
	public void setUnitType(final String unitType) {
		this.unitType = unitType;
	}
	public String getState() {
		return this.state;
	}
	public void setState(final String state) {
		this.state = state;
	}
	public String getZipCode() {
		return this.zipCode;
	}
	public void setZipCode(final String zipCode) {
		this.zipCode = zipCode;
	}
	public String getCountry() {
		return this.country;
	}
	public void setCountry(final String country) {
		this.country = country;
	}
	
}
