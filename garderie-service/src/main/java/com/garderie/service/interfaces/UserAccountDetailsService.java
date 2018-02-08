package com.garderie.service.interfaces;

import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.permissions.ActionPermissions;

import java.util.List;
import java.util.Set;


public interface UserAccountDetailsService {

    UserAccountDetails create(UserAccountDetails object);

    UserAccountDetails find(String id);

    UserAccountDetails findByEmailId(String userName);

    List<UserAccountDetails> findAll();

    UserAccountDetails update(UserAccountDetails object);

    String delete(String id);

    Set<ActionPermissions> getUserActionsByAuthorities(final List<Authority> userAuthorities);

}