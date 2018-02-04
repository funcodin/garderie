package com.garderie.types.org;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.user.info.ContactDetails;

public class OrgOwner {

    private String firstName;
    private String middleName;
    private String lastName;
    private OrgOwnerContactDetails contactDetails;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public OrgOwnerContactDetails getContactDetails() {
        return contactDetails;
    }

    public void setContactDetails(OrgOwnerContactDetails contactDetails) {
        this.contactDetails = contactDetails;
    }
}
