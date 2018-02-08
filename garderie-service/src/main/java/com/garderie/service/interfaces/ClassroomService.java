package com.garderie.service.interfaces;

import com.garderie.types.org.Classroom;

import java.util.Set;

public interface ClassroomService {

    public Classroom create(final Classroom classroom);
    public Classroom update(final Classroom classroom);
    public Set<Classroom> findAllClassroomsByOrgId(final String orgId);
    public void deleteById(final String classroomId);
    public Classroom addStudentsToClassroom(final Classroom classroom);
    public Classroom removeStudentsFromClassroom(final Classroom classroom);


}
