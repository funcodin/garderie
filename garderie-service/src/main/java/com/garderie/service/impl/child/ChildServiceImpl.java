package com.garderie.service.impl.child;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.ChildService;
import com.garderie.service.interfaces.ParentService;
import com.garderie.service.repository.ChildRepository;
import com.garderie.service.util.ControllerUtil;
import com.garderie.service.validator.ChildValidator;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.types.Child;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ChildServiceImpl implements ChildService {

    private static final Logger log = LoggerFactory.getLogger(ChildServiceImpl.class);

    @Autowired
    ChildValidator childValidator;

    @Autowired
    private ChildRepository childRepository;

    @Override
    public Child create(final Child child) {
        final List<GarderieErrors> validationErrors = new ArrayList<>();

        if(Objects.isNull(child)) {
            throw new ServiceException("Cannot create empty child", HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(child.getFirstName())){
            validationErrors.add(GarderieErrors.MISSING_FIRST_NAME);
        }

        if(StringUtils.isBlank(child.getLastName())) {
            validationErrors.add(GarderieErrors.MISSING_LAST_NAME);
        }

        if(CollectionUtils.isNotEmpty(validationErrors)){
            throw new ServiceException("Cannot create child object. Validation failed. "+ validationErrors, HttpStatus.BAD_REQUEST);
        }

        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        final List<Authority> authorityList = jwtTokenData.getAuthorities();
        if( !authorityList.contains(Authority.ROLE_OWNER.getAuthority()) ) {
            throw new ServiceException("Child info can be updated only by owner of the org", HttpStatus.BAD_REQUEST);
        }

        child.setId(UUID.randomUUID().toString());
        child.setOrgId(jwtTokenData.getOrgId());
        child.setModifiedDate(new Date());
        child.setCreatedDate(new Date());
        return this.childRepository.save(child);
    }


    @Override
    public Child update(final Child child) {
        final List<GarderieErrors> validationErrors = new ArrayList<>();

        if(Objects.isNull(child)) {
            throw new ServiceException("Cannot create empty child", HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(child.getId())) {
            throw new ServiceException("Cannot update child without id ", HttpStatus.BAD_REQUEST);
        }

        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        final List<Authority> authorityList = jwtTokenData.getAuthorities();
        if( !authorityList.contains(Authority.ROLE_OWNER.getAuthority()) ) {
            throw new ServiceException("Child info can be updated only by owner of the org", HttpStatus.BAD_REQUEST);
        }

        final Child existingChild = this.childRepository.findOne(child.getId());
        if(!existingChild.getOrgId().equals(jwtTokenData.getOrgId())){
            throw new ServiceException("Child can be updated only by the owner of the same org", HttpStatus.FORBIDDEN);
        }


        if(StringUtils.isNotBlank(child.getFirstName())) {
            existingChild.setFirstName(child.getFirstName());
        }

        if(StringUtils.isNotBlank(child.getMiddleName())) {
            existingChild.setMiddleName(child.getMiddleName());
        }

        if(StringUtils.isNotBlank(child.getLastName())) {
            existingChild.setLastName(child.getLastName());
        }

        if(CollectionUtils.isNotEmpty(child.getParentIds())) {
            existingChild.setParentIds(child.getParentIds());
        }

        if(CollectionUtils.isNotEmpty(child.getClassroomIds())) {
            existingChild.setClassroomIds(child.getClassroomIds());
        }

        if(Objects.nonNull(child.getAddress())) {
            existingChild.setAddress(child.getAddress());
        }

        if(Objects.nonNull(child.getMedicalInformation())) {
            existingChild.setMedicalInformation(child.getMedicalInformation());
        }

        existingChild.setModifiedDate(new Date());

        final Child updatedChild = this.childRepository.save(child);

        return updatedChild;
    }

    @Override
    public Child findChildById(String childId) {
        return this.childRepository.findOne(childId);
    }

    @Override
    public void deleteChildById(String childId) {
        this.childRepository.delete(childId);
    }

    @Override
    public Set<Child> getAllChildrensInClassroom(String classroomId) {
        return this.childRepository.findChildByClassroomIdsIn(classroomId);
    }

    @Override
    public Set<Child> getAllChildrensInOrganisation(String orgId) {
        return this.childRepository.findByOrgId(orgId);
    }
}
