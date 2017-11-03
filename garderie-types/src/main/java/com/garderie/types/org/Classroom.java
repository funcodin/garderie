package com.garderie.types.org;

import java.util.List;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;

public class Classroom extends AbstractPersistable{
	private String orgId;
	private String name;
	private String description;
	private Address address;
	private List<String> teacherIds;
	
	public String getOrgId() {
		return this.orgId;
	}
	public void setOrgId(final String orgId) {
		this.orgId = orgId;
	}
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public String getDescription() {
		return this.description;
	}
	public void setDescription(final String description) {
		this.description = description;
	}
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(final Address address) {
		this.address = address;
	}
	public List<String> getTeacherIds() {
		return this.teacherIds;
	}
	public void setTeacherIds(final List<String> teacherIds) {
		this.teacherIds = teacherIds;
	}
	
}
