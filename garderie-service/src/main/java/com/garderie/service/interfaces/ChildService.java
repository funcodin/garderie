package com.garderie.service.interfaces;

import com.garderie.types.user.types.Child;

import java.util.Set;

public interface ChildService {

    Child create(final Child child);
    Child update(final Child child);
    void deleteChildById(final String childId);
    Child findChildById( final String childId );
    Set<Child> getAllChildrensInClassroom(final String classroomId);
    Set<Child> getAllChildrensInOrganisation(final String orgId);

}
