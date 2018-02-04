package com.garderie.service.repository;

import com.garderie.types.security.auth.permissions.UserPermissions;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserPermissionsRepository extends MongoRepository<UserPermissions, String> {
    public UserPermissions findByEmailId(final String emailId);

    public void deleteByEmailId(final String emailId);

}
