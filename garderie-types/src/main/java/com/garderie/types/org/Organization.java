package com.garderie.types.org;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;

public class Organization extends AbstractPersistable{

	private String name;
	private Address address;
	private String ownerId;
	
	public String getName() {
		return this.name;
	}
	public void setName(final String name) {
		this.name = name;
	}
	public Address getAddress() {
		return this.address;
	}
	public void setAddress(final Address address) {
		this.address = address;
	}
	public String getOwnerId() {
		return this.ownerId;
	}
	public void setOwnerId(final String ownerId) {
		this.ownerId = ownerId;
	}

}
