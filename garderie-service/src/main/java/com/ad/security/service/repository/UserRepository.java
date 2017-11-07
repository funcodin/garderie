package com.ad.security.service.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.ad.security.types.User;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    User findByUsername(final String userName);
}
