package com.garderie.types.org;

import java.util.List;
import java.util.Set;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;

public class Classroom extends AbstractPersistable{
	private String orgId;
	private String name;
	private Set<String> studentIds;

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
	public Set<String> getStudentIds() {
		return studentIds;
	}

	public void setStudentIds(Set<String> studentIds) {
		this.studentIds = studentIds;
	}
}
