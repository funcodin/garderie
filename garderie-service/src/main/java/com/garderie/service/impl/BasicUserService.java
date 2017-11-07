package com.garderie.service.impl;

import java.util.Date;
import java.util.List;

import com.garderie.service.UserRepository;
import com.garderie.types.security.auth.UserAuth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class BasicUserService implements UserService {

    private final UserRepository repository;

    @Autowired
    public BasicUserService(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserAuth create(final UserAuth user) {
        user.setCreatedDate( new Date());
        return repository.save(user);
    }

    @Override
    public UserAuth find(final String id) {
        return repository.findOne(id);
    }

    @Override
    public UserAuth findByEmailId(final String userName) {
        return repository.findByEmailId(userName);
    }

    @Override
    public List<UserAuth> findAll() {
        return repository.findAll();
    }

    @Override
    public UserAuth update(final String id, final UserAuth user) {
        return user;
    }

    @Override
    public String delete(final String id) {
        repository.delete(id);
        return id;
    }
}
