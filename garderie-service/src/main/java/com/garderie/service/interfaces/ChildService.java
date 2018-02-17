package com.garderie.service.interfaces;

import com.garderie.types.user.types.Child;

public interface ChildService {

    Child create(final Child child);
    Child update(final Child child);
    void deleteChildById(final String childId);

}
