package com.garderie.service.repository.organisation;

import com.garderie.types.org.Classroom;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClassroomRepository extends MongoRepository<Classroom, String>{
    public Set<Classroom> findClassroomByOrgId(final String orgId);
}
