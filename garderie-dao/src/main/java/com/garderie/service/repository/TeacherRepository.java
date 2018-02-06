package com.garderie.service.repository;

import com.garderie.types.user.types.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherRepository extends MongoRepository<Teacher, String> {

    public Teacher findTeacherById(final String id);
}
