package com.garderie.types.user.info;

public class EmergencyContact {

	private String cellPhone;
	private String workPhone;
	private String homePhone;
	private String firstName;
	private String lastName;
	
	public String getCellPhone() {
		return this.cellPhone;
	}
	public void setCellPhone(final String cellPhone) {
		this.cellPhone = cellPhone;
	}
	public String getWorkPhone() {
		return this.workPhone;
	}
	public void setWorkPhone(final String workPhone) {
		this.workPhone = workPhone;
	}
	public String getHomePhone() {
		return this.homePhone;
	}
	public void setHomePhone(final String homePhone) {
		this.homePhone = homePhone;
	}
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(final String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(final String lastName) {
		this.lastName = lastName;
	}
	
}
