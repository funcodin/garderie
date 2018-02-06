package com.garderie.service.impl;

import com.garderie.service.converter.ConverterFactory;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.*;
import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.UserAuthentication;
import com.garderie.types.security.auth.UserSalt;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.permissions.UserPermissions;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Set;

@Service
public class SignUpServiceImpl implements SignUpService {

    @Autowired
    private UserSaltService userSaltService;

    @Autowired
    private ConverterFactory converterFactory;

    @Autowired
    private UserAccountDetailsService userAccountDetailsService;

    @Autowired
    private UserSaltHashingService userSaltHashingService;

    @Autowired
    private UserPermissionsService userPermissionsService;

    @Override
    public UserAccountDetails signUpOwner(final SignUpDTO signUpDTO) {

        final UserAccountDetails createdUserAccountDetails = this.signUpUser(signUpDTO, "ORG_OWNER",null);

        //TODO send email with secret code
        //TODO if email fails delete user salt and user auth

        return createdUserAccountDetails;
    }

    @Override
    public UserAccountDetails signUpParent(final SignUpDTO signUpDTO, final String orgId) {
        final UserAccountDetails createdUserAccountDetails = this.signUpUser(signUpDTO, "PARENT", orgId);
        //TODO send email with secret code
        //TODO if email fails delete user salt and user auth
        return createdUserAccountDetails;

    }

    @Override
    public UserAccountDetails signUpTeacher(final SignUpDTO signUpDTO, final String orgId) {
        final UserAccountDetails createdUserAccountDetails = this.signUpUser(signUpDTO, "TEACHER", orgId);

        //TODO send email with secret code
        //TODO if email fails delete user salt and user auth
        return createdUserAccountDetails;

    }

    private UserAccountDetails signUpUser(final SignUpDTO signUpDTO, final String userType, final String orgId) {
        final UserSalt userSaltCheck = this.userSaltService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.nonNull(userSaltCheck)) {
            throw new ServiceException("Email already taken", HttpStatus.BAD_REQUEST);
        }

        final UserAccountDetails userAccountDetails = (UserAccountDetails) this.converterFactory.getConverter(userType).convert(signUpDTO);

        final UserAccountDetails createdUserAccountDetails = this.userAccountDetailsService.create(userAccountDetails);

        final Set<ActionPermissions> actionPermissions = this.userPermissionsService.getUserActionsByUserAuth(createdUserAccountDetails);
        if (CollectionUtils.isEmpty(actionPermissions)) {
            //this means we dont have mapping for authority to permissions
            //TODO throw error
        }
        final UserPermissions userPermissions = new UserPermissions();
        userPermissions.setEmailId(userAccountDetails.getEmailId());
        userPermissions.setActionPermissions(actionPermissions);
        userPermissions.setOrganisationId(orgId);
        this.userPermissionsService.create(userPermissions);

        return createdUserAccountDetails;
    }

    @Override
    public UserAuthentication signUpUserWithCode(final SignUpDTO signUpDTO) {
        final UserAuthentication userAuthentication = new UserAuthentication();
        final UserSalt userSaltCheck = this.userSaltService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.nonNull(userSaltCheck)) {
            throw new ServiceException("Email already taken", HttpStatus.BAD_REQUEST);
        }

        final UserSalt userSalt = (UserSalt) this.converterFactory.getConverter("USER_SALT").convert(signUpDTO);

        final String salt = this.userSaltHashingService.generateSalt();
        userSalt.setSalt(salt);

        final UserSalt createdUserSalt = this.userSaltService.create(userSalt);

        final UserAccountDetails userAccountDetails = this.userAccountDetailsService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.isNull(userAccountDetails)) {
            throw new ServiceException("User does not exists");
        }

        if (userAccountDetails.isEnabled()) {
            throw new ServiceException("User already active", HttpStatus.BAD_REQUEST);
        }

        if (!userAccountDetails.getSecretCode().equals(signUpDTO.getSecretCode())) {
            throw new ServiceException("Invalid code", HttpStatus.BAD_REQUEST);
        }

        userAccountDetails.setActive(true);
        final String encryptedPassword = this.userSaltHashingService.generateHashWithSalt(signUpDTO.getPassword(), salt);
        userAccountDetails.setEncryptedPassword(encryptedPassword);

        final UserAccountDetails updatedUserAccountDetails = this.userAccountDetailsService.update(userAccountDetails);

        //create user permissions
        final UserPermissions userPermissions = this.userPermissionsService.findByEmailId(signUpDTO.getEmailId());

        if(Objects.isNull(userPermissions)){
            throw new ServiceException("Unable to assign user permissions",HttpStatus.BAD_REQUEST);
        }
        userAuthentication.setUserAccountDetails(updatedUserAccountDetails);
        userAuthentication.setUserPermissions(userPermissions);
        userAuthentication.setUserSalt(createdUserSalt);
        //Generate and return token
        return userAuthentication;


    }
}
