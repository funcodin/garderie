package com.garderie.service.impl;

import com.garderie.service.interfaces.ParentService;
import com.garderie.service.repository.ParentRepository;
import com.garderie.types.user.types.Parent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ParentServiceImpl implements ParentService {

   @Autowired
    ParentRepository parentRepository;

    @Override
    public Parent create(final Parent parent) {
        parent.setCreatedDate(new Date());
        parent.setModifiedDate(new Date());
        parent.setId(UUID.randomUUID().toString());
        return this.parentRepository.save(parent);
    }
}
