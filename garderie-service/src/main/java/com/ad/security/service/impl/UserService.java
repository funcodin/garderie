package com.ad.security.service.impl;

import java.util.List;

import com.ad.security.types.User;

public interface UserService {

    User create(User object);

    User find(String id);

    User findByUsername(String userName);

    List<User> findAll();

    User update(String id, User object);

    String delete(String id);
}