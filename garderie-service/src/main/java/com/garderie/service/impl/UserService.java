package com.garderie.service.impl;

import com.garderie.types.security.auth.UserAuth;

import java.util.List;


public interface UserService {

    UserAuth create(UserAuth object);

    UserAuth find(String id);

    UserAuth findByEmailId(String userName);

    List<UserAuth> findAll();

    UserAuth update(String id, UserAuth object);

    String delete(String id);
}