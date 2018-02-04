package com.garderie.service.impl.account;

import com.garderie.service.interfaces.UserAccountDetailsService;
import com.garderie.service.repository.UserRepository;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


@Service
public class UserAccountDetailsServiceImpl implements UserAccountDetailsService, UserDetailsService {

    @Autowired
    private  UserRepository repository;

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
    public UserAccountDetails update(final String id, final UserAccountDetails user) {
        return user;
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

    public UserAccountDetails createParentWithCode(final UserAccountDetails userAccountDetails) {
        this.prepopulateUserAuth(userAccountDetails);
        userAccountDetails.setAuthorities(Arrays.asList(Authority.ROLE_PARENT));
        return this.repository.save(userAccountDetails);
    }

    public UserAccountDetails createTeacherWithCode(final UserAccountDetails userAccountDetails) {
        this.prepopulateUserAuth(userAccountDetails);
        userAccountDetails.setAuthorities(Arrays.asList(Authority.ROLE_TEACHER));
        return this.repository.save(userAccountDetails);
    }

    private void prepopulateUserAuth(final UserAccountDetails userAccountDetails) {
        userAccountDetails.setActive(false);
        userAccountDetails.setAccountNonLocked(true);
        userAccountDetails.setAccountNonExpired(true);
        userAccountDetails.setCredentialsNonExpired(true);
        userAccountDetails.setAccountExpirationDate(DateUtils.addMonths(new Date(), 6));
        userAccountDetails.setSecretCode(RandomStringUtils.randomAlphabetic(5));
    }

}
