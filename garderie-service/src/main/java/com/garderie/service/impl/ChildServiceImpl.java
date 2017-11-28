package com.garderie.service.impl;

import com.garderie.service.interfaces.ChildService;
import com.garderie.service.interfaces.ParentService;
import com.garderie.service.repository.ChildRepository;
import com.garderie.service.repository.ParentRepository;
import com.garderie.types.user.types.Child;
import com.garderie.types.user.types.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class ChildServiceImpl implements ChildService {

    @Autowired
    ChildRepository childRepository;

    @Autowired
    ParentService parentService;

    @Override
    public Child create(final Child child) {
        child.setId(UUID.randomUUID().toString());
        child.setCreatedDate(new Date());
        child.setModifiedDate(new Date());
        List<Parent> parents = child.getParents().stream().forEach(parent -> this.parentService.create(parent));
        return childRepository.save(child);
    }
}
