package com.hotwheels.repository.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.hotwheels.repository.types.UserAuth;

public interface UserAuthRepository extends MongoRepository<UserAuth,String>{

	public UserAuth findByUsername( final String username );

}
