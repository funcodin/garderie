package com.garderie.types.security.auth.permissions;

import com.garderie.types.AbstractPersistable;

import java.util.Set;

public class UserPermissions extends AbstractPersistable {

    private String emailId;
    private Set<ActionPermissions> actionPermissions;
    private String organisationId;


    public String getEmailId() {
        return this.emailId;
    }

    public void setEmailId(final String emailId) {
        this.emailId = emailId;
    }

    public Set<ActionPermissions> getActionPermissions() {
        return this.actionPermissions;
    }

    public void setActionPermissions(final Set<ActionPermissions> actionPermissions) {
        this.actionPermissions = actionPermissions;
    }

    public String getOrganisationId() {
        return this.organisationId;
    }

    public void setOrganisationId(final String organisationId) {
        this.organisationId = organisationId;
    }
}
