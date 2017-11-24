package com.garderie.service.repository;

import com.garderie.types.user.types.Child;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChildRepository extends MongoRepository<Child,String> {
}
