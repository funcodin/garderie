package com.garderie.types.org;

import com.garderie.types.AbstractPersistable;

public class Organisation extends AbstractPersistable {

    private String orgName;
    private OrgOwner orgOwner;
    private OrganisationAddress organisationAddress;

    public OrganisationAddress getOrganisationAddress() {
        return organisationAddress;
    }

    public void setOrganisationAddress(OrganisationAddress organisationAddress) {
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
