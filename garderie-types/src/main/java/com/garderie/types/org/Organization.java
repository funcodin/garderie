package com.garderie.types.org;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;

public class Organization extends AbstractPersistable{

	private String orgName;
	private OrgOwner orgOwner;
	private Address address;

	public Address getAddress() {
		return this.address;
	}
	public void setAddress(final Address address) {
		this.address = address;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(final String orgName) {
		this.orgName = orgName;
	}

	public OrgOwner getOrgOwner() {
		return this.orgOwner;
	}

	public void setOrgOwner(final OrgOwner orgOwner) {
		this.orgOwner = orgOwner;
	}
}
