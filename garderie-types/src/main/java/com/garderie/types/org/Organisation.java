package com.garderie.types.org;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;

public class Organisation extends AbstractPersistable {

    private String orgOwnerId;
    private String orgName;
    private Address organisationAddress;

    public String getOrgOwnerId() {
        return orgOwnerId;
    }

    public void setOrgOwnerId(String orgOwnerId) {
        this.orgOwnerId = orgOwnerId;
    }

    public Address getOrganisationAddress() {
        return organisationAddress;
    }

    public void setOrganisationAddress(Address organisationAddress) {
        this.organisationAddress = organisationAddress;
    }

    public String getOrgName() {
        return this.orgName;
    }

    public void setOrgName(final String orgName) {
        this.orgName = orgName;
    }
}
