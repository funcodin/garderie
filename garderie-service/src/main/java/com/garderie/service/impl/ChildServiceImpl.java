package com.garderie.service.impl;

import com.garderie.service.repository.ChildRepository;
import com.garderie.types.user.types.Child;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ChildServiceImpl implements ChildService{

    @Autowired
    ChildRepository childRepository;

    @Override
    public Child create(final Child child) {
        child.setId(UUID.randomUUID().toString());
        child.setCreatedDate(new Date());
        child.setModifiedDate(new Date());
        return childRepository.save(child);
    }
}
