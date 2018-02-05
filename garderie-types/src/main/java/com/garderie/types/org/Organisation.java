package com.garderie.types.org;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.Address;

public class Organisation extends AbstractPersistable {

    private String orgName;
    private OrgOwner orgOwner;
    private Address organisationAddress;

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

    public OrgOwner getOrgOwner() {
        return this.orgOwner;
    }

    public void setOrgOwner(final OrgOwner orgOwner) {
        this.orgOwner = orgOwner;
    }
}
