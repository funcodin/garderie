package com.garderie.service.impl.account;

import com.garderie.service.interfaces.UserAccountDetailsService;
import com.garderie.service.repository.UserAccountDetailsRepository;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserAccountDetailsServiceImpl implements UserAccountDetailsService, UserDetailsService {

    @Autowired
    private UserAccountDetailsRepository repository;

    @Override
    public UserAccountDetails create(final UserAccountDetails user) {
        user.setCreatedDate(new Date());
        return repository.save(user);
    }

    @Override
    public UserAccountDetails find(final String id) {
        return repository.findOne(id);
    }

    @Override
    public UserAccountDetails findByEmailId(final String emailId) {
        return repository.findByEmailId(emailId);
    }

    @Override
    public List<UserAccountDetails> findAll() {
        return repository.findAll();
    }

    @Override
    public UserAccountDetails update(final UserAccountDetails user) {
        user.setModifiedDate(new Date());
        return this.repository.save(user);
    }

    @Override
    public String delete(final String id) {
        repository.delete(id);
        return id;
    }

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        final UserAccountDetails user = this.findByEmailId(username);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("User with username:" + username + " not found");
        }
    }

    @Override
    public Set<ActionPermissions> getUserActionsByAuthorities(final List<Authority> userAuthorities){

        final Set<ActionPermissions> actionPermissions = new HashSet<>();

        if(CollectionUtils.isEmpty(userAuthorities)) {
            //TODO throw error
        }

        if(userAuthorities.contains(Authority.ROLE_OWNER)) {
            actionPermissions.add(ActionPermissions.ADD_OWNER);
            actionPermissions.add(ActionPermissions.ADD_ACTIVITY);
            actionPermissions.add(ActionPermissions.ADD_ORGANISATION);
            actionPermissions.add(ActionPermissions.ADD_PARENT);
            actionPermissions.add(ActionPermissions.ADD_TEACHER);
            actionPermissions.add(ActionPermissions.ADD_PICTURE);
        }

        if(userAuthorities.contains(Authority.ROLE_PARENT)) {
            actionPermissions.add(ActionPermissions.VIEW_ACTIVITY);
            actionPermissions.add(ActionPermissions.VIEW_FEED);
            actionPermissions.add(ActionPermissions.UPDATE_TEACHER);
        }

        if(userAuthorities.contains(Authority.ROLE_TEACHER)) {
            actionPermissions.add(ActionPermissions.ADD_ACTIVITY);
            actionPermissions.add(ActionPermissions.ADD_PICTURE);
        }

        return actionPermissions;

    }


}
