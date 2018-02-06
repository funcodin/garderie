package com.garderie.service.interfaces;

import com.garderie.types.security.auth.UserAccountDetails;

import java.util.List;


public interface UserAccountDetailsService {

    UserAccountDetails create(UserAccountDetails object);

    UserAccountDetails find(String id);

    UserAccountDetails findByEmailId(String userName);

    List<UserAccountDetails> findAll();

    UserAccountDetails update(UserAccountDetails object);

    String delete(String id);

}