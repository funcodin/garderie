package com.garderie.service.repository;

import com.garderie.types.user.types.Parent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParentRepository extends MongoRepository<Parent, String> {
}
