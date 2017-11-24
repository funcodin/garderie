package com.garderie.types.org;

import java.util.List;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;

public class Classroom extends AbstractPersistable{
	private String orgId;
	private String name;
	private String description;

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

}
