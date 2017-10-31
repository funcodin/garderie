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
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<String> getTeacherIds() {
		return teacherIds;
	}
	public void setTeacherIds(List<String> teacherIds) {
		this.teacherIds = teacherIds;
	}
	
}
