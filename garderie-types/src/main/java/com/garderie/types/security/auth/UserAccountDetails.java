package com.garderie.types.security.auth;

import java.util.Date;
import java.util.List;
import java.util.Set;

import com.garderie.types.AbstractPersistable;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.security.core.userdetails.UserDetails;

public class UserAccountDetails extends AbstractPersistable implements UserDetails{

    @Indexed(unique = true)
	private String emailId; // used as username
	private List<Authority> authorities;
	private String encryptedPassword; // encrypted password
	private Boolean isActive; // will be set to false when secret code is generated and will be set to true when secret code is used and will never go back to false;
	private String secretCode; // generated on signup request.
	private Boolean accountNonExpired; // will be set to true if expired date < today's date else set to false
	private Boolean accountNonLocked; //will be set to false if payment is not done or else set to true
	private Boolean credentialsNonExpired; // will be set to false if password tries more than 5 times else true
	private Date accountExpirationDate; // date when account is set to be expired.
	private String organisationId;
	private Set<ActionPermissions> actionPermissions;

	public List<Authority> getAuthorities() {
		return this.authorities;
	}

    @Override
    public String getPassword() {
        return this.encryptedPassword;
    }

    @Override
    public String getUsername() {
        return this.emailId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return this.accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return this.credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive;
    }

    public void setAuthorities(final List<Authority> authorities) {
		this.authorities = authorities;
	}
	public String getEncryptedPassword() {
		return this.encryptedPassword;
	}
	public void setEncryptedPassword(final String encryptedPassword) {
		this.encryptedPassword = encryptedPassword;
	}

	public String getEmailId() {
		return this.emailId;
	}
	public void setEmailId(final String emailId) {
		this.emailId = emailId;
	}
	public String getSecretCode() {
		return this.secretCode;
	}
	public void setSecretCode(final String secretCode) {
		this.secretCode = secretCode;
	}
    public Boolean getActive() {
        return this.isActive;
    }
    public void setActive( final Boolean active) {
        this.isActive = active;
    }

	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	public Date getAccountExpirationDate() {
		return this.accountExpirationDate;
	}

	public void setAccountExpirationDate(final Date accountExpirationDate) {
		this.accountExpirationDate = accountExpirationDate;
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
