package com.garderie.service.impl.account;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.UserPermissionsService;
import com.garderie.service.repository.UserPermissionsRepository;
import com.garderie.service.validator.UserPermissionsValidator;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.permissions.UserPermissions;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserPermissionsServiceImpl implements UserPermissionsService{

    @Autowired
    UserPermissionsValidator userPermissionsValidator;

   @Autowired
   UserPermissionsRepository userPermissionsRepository;

    @Override
    public UserPermissions create( final UserPermissions userPermissions) {
        final List<GarderieErrors> validationErrors = this.userPermissionsValidator.validatePreSave(userPermissions);
        if(CollectionUtils.isNotEmpty(validationErrors)){
            throw  new ServiceException(validationErrors.toString(), HttpStatus.BAD_REQUEST);
        }
        final UserPermissions createUserPermissions = this.userPermissionsRepository.save(userPermissions);
        return createUserPermissions;
    }

    @Override
    public UserPermissions update(final UserPermissions userPermissions) {
        final List<GarderieErrors> validationErrors = this.userPermissionsValidator.validatePreSave(userPermissions);
        if(CollectionUtils.isNotEmpty(validationErrors)){
            throw  new ServiceException(validationErrors.toString(), HttpStatus.BAD_REQUEST);
        }
        userPermissions.setModifiedDate(new Date());
        final UserPermissions updatedUserPermissions = this.userPermissionsRepository.save(userPermissions);
        return updatedUserPermissions;
    }

    @Override
    public void deleteByEmailId(String emailId) {

    }

    @Override
    public UserPermissions findByEmailId(String emailId) {
        final UserPermissions userPermissions = this.userPermissionsRepository.findByEmailId(emailId);
        if(Objects.isNull(userPermissions)){
            throw new ServiceException("User Permissions Not found",HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return userPermissions;
    }

    @Override
    public Set<ActionPermissions> getUserActionsByUserAuth(final UserAccountDetails userAccountDetails){

        final Set<ActionPermissions> actionPermissions = new HashSet<>();

        if(Objects.nonNull(userAccountDetails)){
            //TODO throw error
        }

        if(CollectionUtils.isEmpty(userAccountDetails.getAuthorities())) {
            //TODO throw error
        }

        if(userAccountDetails.getAuthorities().contains(Authority.ROLE_OWNER)) {
            actionPermissions.add(ActionPermissions.ADD_ACTIVITY);
            actionPermissions.add(ActionPermissions.ADD_ORGANISATION);
            actionPermissions.add(ActionPermissions.ADD_PARENT);
            actionPermissions.add(ActionPermissions.ADD_TEACHER);
            actionPermissions.add(ActionPermissions.ADD_PICTURE);
        }

        if(userAccountDetails.getAuthorities().contains(Authority.ROLE_PARENT)) {
            actionPermissions.add(ActionPermissions.VIEW_ACTIVITY);
            actionPermissions.add(ActionPermissions.VIEW_FEED);
        }

        if(userAccountDetails.getAuthorities().contains(Authority.ROLE_TEACHER)) {
            actionPermissions.add(ActionPermissions.ADD_ACTIVITY);
            actionPermissions.add(ActionPermissions.ADD_PICTURE);
        }

        return actionPermissions;

    }


}
