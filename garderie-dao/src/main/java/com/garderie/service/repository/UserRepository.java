package com.garderie.service.repository;

import com.garderie.types.security.auth.UserAccountDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserAccountDetails, String> {
    UserAccountDetails findByEmailId(final String emailId );
}
