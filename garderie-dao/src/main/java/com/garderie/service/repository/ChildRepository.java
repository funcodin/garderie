package com.garderie.service.repository;

import com.garderie.types.user.types.Child;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ChildRepository extends MongoRepository<Child,String> {

    public Set<Child> findChildByClassroomIdsIn(final String classroomId);
    public Set<Child> findByOrgId(final String orgId);
}
