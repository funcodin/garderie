package com.garderie.service.repository;

import com.garderie.types.security.auth.UserAuth;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<UserAuth, String> {

    UserAuth findByEmailId( final String emailId );
}
