package com.garderie.types.org;

public class OrganisationAddress {

	private String streetAddress;
	private String city;
	private String state;
	private String zipCode;
	private String country;
	
	public String getStreetAddress() {
		return this.streetAddress;
	}
	public void setStreetAddress(String streetAddress) {
		this.streetAddress = streetAddress;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
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
