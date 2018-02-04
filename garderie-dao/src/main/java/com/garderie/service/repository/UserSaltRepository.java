package com.garderie.service.repository;

import com.garderie.types.security.auth.UserSalt;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserSaltRepository extends MongoRepository<UserSalt, String> {

    public UserSalt findByEmailId( final String emailId );
    public void deleteByEmailId(final String emailId);

}
