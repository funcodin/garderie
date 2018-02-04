package com.garderie.service.validator;

import com.garderie.types.AbstractPersistable;

import java.util.Date;
import java.util.Objects;
import java.util.UUID;

public abstract class AbstractValidator {

    public void setDefaultValues(final AbstractPersistable abstractPersistable){

        if(Objects.isNull(abstractPersistable.getId()))
            abstractPersistable.setId(UUID.randomUUID().toString());

        if(Objects.isNull(abstractPersistable.getCreatedDate()))
            abstractPersistable.setCreatedDate(new Date());

        if(Objects.isNull(abstractPersistable.getModifiedDate()))
            abstractPersistable.setModifiedDate(new Date());
    }

}
