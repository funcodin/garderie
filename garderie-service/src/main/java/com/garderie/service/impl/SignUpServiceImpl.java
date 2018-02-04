package com.garderie.service.impl;

import com.garderie.service.converter.ConverterFactory;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.*;
import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.UserAuthentication;
import com.garderie.types.security.auth.permissions.UserPermissions;
import com.garderie.types.security.auth.UserSalt;
import com.garderie.types.security.auth.permissions.ActionPermissions;
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
        final UserSalt userSaltCheck = this.userSaltService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.nonNull(userSaltCheck)) {
            throw new ServiceException("Email already taken", HttpStatus.BAD_REQUEST);
        }

        final UserAccountDetails userAccountDetails = (UserAccountDetails) this.converterFactory.getConverter("ORG_OWNER").convert(signUpDTO);

        final UserAccountDetails createdUserAccountDetails = this.userAccountDetailsService.create(userAccountDetails);

        //TODO send email with secret code

        //TODO if email fails delete user salt and user auth

        return createdUserAccountDetails;
    }

    @Override
    public void signUpParent(final SignUpDTO signUpDTO) {

        final UserSalt userSaltCheck = this.userSaltService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.nonNull(userSaltCheck)) {
            throw new ServiceException("Email already taken", HttpStatus.BAD_REQUEST);
        }

        final UserAccountDetails userAccountDetails = this.userAccountDetailsService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.nonNull(userAccountDetails)) {
            //TODO throw error
        }

        final UserAccountDetails parentUserAccountDetails = new UserAccountDetails();
        parentUserAccountDetails.setEmailId(signUpDTO.getEmailId());
        this.userAccountDetailsService.createParentWithCode(userAccountDetails);

    }

    @Override
    public void signUpTeacher(SignUpDTO signUpDTO) {
        final UserSalt userSaltCheck = this.userSaltService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.nonNull(userSaltCheck)) {
            throw new ServiceException("Email already taken", HttpStatus.BAD_REQUEST);
        }

        final UserAccountDetails userAccountDetails = this.userAccountDetailsService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.nonNull(userAccountDetails)) {
            //TODO throw error
        }

        final UserAccountDetails parentUserAccountDetails = new UserAccountDetails();
        parentUserAccountDetails.setEmailId(signUpDTO.getEmailId());
        this.userAccountDetailsService.createTeacherWithCode(userAccountDetails);

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

        this.userSaltService.create(userSalt);

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

        final UserAccountDetails updatedUserAccountDetails = this.userAccountDetailsService.create(userAccountDetails);

        //create user permissions
        final Set<ActionPermissions> actionPermissions = this.userPermissionsService.getUserActionsByUserAuth(updatedUserAccountDetails);
        if (CollectionUtils.isEmpty(actionPermissions)) {
            //this means we dont have mapping for authority to permissions
            //TODO throw error
        }
        final UserPermissions userPermissions = new UserPermissions();
        userPermissions.setEmailId(userAccountDetails.getEmailId());
        userPermissions.setActionPermissions(actionPermissions);

        userAuthentication.setUserSalt(userSalt);
        userAuthentication.setUserAccountDetails(updatedUserAccountDetails);
        userAuthentication.setUserPermissions(this.userPermissionsService.create(userPermissions));
        //Generate and return token
        return userAuthentication;


    }
}
