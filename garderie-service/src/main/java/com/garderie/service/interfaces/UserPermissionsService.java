package com.garderie.service.interfaces;

import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.permissions.UserPermissions;
import com.garderie.types.security.auth.permissions.ActionPermissions;

import java.util.Set;

public interface UserPermissionsService {

    public UserPermissions create( final UserPermissions userPermissions );
    public UserPermissions update( final UserPermissions userPermissions);
    public void deleteByEmailId( final String emailId );
    public UserPermissions findByEmailId( final String emailId);
    public Set<ActionPermissions> getUserActionsByUserAuth(final UserAccountDetails userAccountDetails);
}
