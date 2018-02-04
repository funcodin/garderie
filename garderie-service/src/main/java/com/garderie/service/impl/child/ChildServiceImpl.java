package com.garderie.service.impl.child;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.interfaces.ChildService;
import com.garderie.service.interfaces.ParentService;
import com.garderie.service.repository.ChildRepository;
import com.garderie.service.validator.ChildValidator;
import com.garderie.types.user.types.Child;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChildServiceImpl implements ChildService {

    private static final Logger log = LoggerFactory.getLogger(ChildServiceImpl.class);

    @Autowired
    ChildValidator childValidator;

    @Autowired
    ChildRepository childRepository;

    @Autowired
    ParentService parentService;

    @Override
    public Child create(final Child child) {
        final List<GarderieErrors> validationErrors = this.childValidator.validatePreSave(child);

        if(CollectionUtils.isNotEmpty(validationErrors)){
            log.error("Cannot create child object. Validation failed. "+ validationErrors);
           //TODO throw exception
        }
        child.getParents().stream().forEach(parent -> this.parentService.create(parent));
        return this.childRepository.save(child);
    }
}
