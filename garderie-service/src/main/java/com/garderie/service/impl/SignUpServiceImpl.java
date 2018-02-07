package com.garderie.service.impl;

import com.garderie.service.converter.ConverterFactory;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.*;
import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.org.OrgOwner;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.UserAuthentication;
import com.garderie.types.security.auth.UserSalt;
import com.garderie.types.security.auth.permissions.ActionPermissions;
import com.garderie.types.security.auth.permissions.UserPermissions;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.types.Teacher;
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

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private OrgOwnerService orgOwnerService;

    @Override
    public UserAccountDetails signUpOwner(final SignUpDTO signUpDTO) {

        final UserAccountDetails createdUserAccountDetails = this.signUpUser(signUpDTO, "ORG_OWNER",null);

        final OrgOwner orgOwner = new OrgOwner();
        orgOwner.setId(createdUserAccountDetails.getId());
        orgOwner.setEmailId(createdUserAccountDetails.getEmailId());
        this.orgOwnerService.createOrgOwnerInternally(orgOwner);

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
    public UserAccountDetails signUpTeacher(final SignUpDTO signUpDTO, final JwtTokenData jwtTokenData) {
        //Create Teacher User accounts
        final UserAccountDetails createdUserAccountDetails = this.signUpUser(signUpDTO, "TEACHER", jwtTokenData.getOrgId());
        //Create Teacher info
        final Teacher teacher = new Teacher();
        teacher.setId(createdUserAccountDetails.getId());
        teacher.setEmailId(createdUserAccountDetails.getEmailId());
        teacher.setOrgId(jwtTokenData.getOrgId());
        this.teacherService.create(teacher, jwtTokenData);

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
            throw new ServiceException("Cannot find action permission for authority", HttpStatus.INTERNAL_SERVER_ERROR);
            //this means we dont have mapping for authority to permissions
        }
        final UserPermissions userPermissions = new UserPermissions();
        userPermissions.setId(createdUserAccountDetails.getId());
        userPermissions.setEmailId(userAccountDetails.getEmailId());
        userPermissions.setActionPermissions(actionPermissions);
        userPermissions.setOrganisationId(orgId);
        this.userPermissionsService.create(userPermissions);

        return createdUserAccountDetails;
    }

    @Override
    public UserAuthentication signUpUserWithCode(final SignUpDTO signUpDTO) {
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


        final UserPermissions userPermissions = this.userPermissionsService.findByEmailId(signUpDTO.getEmailId());

        if(Objects.isNull(userPermissions)){
            throw new ServiceException("Unable to assign user permissions",HttpStatus.BAD_REQUEST);
        }

        final UserSalt userSaltCheck = this.userSaltService.findByEmailId(signUpDTO.getEmailId());

        if (Objects.nonNull(userSaltCheck)) {
            throw new ServiceException("Email already taken", HttpStatus.BAD_REQUEST);
        }

        final UserSalt userSalt = (UserSalt) this.converterFactory.getConverter("USER_SALT").convert(signUpDTO);

        final String salt = this.userSaltHashingService.generateSalt();
        userSalt.setId(userAccountDetails.getId());
        userSalt.setSalt(salt);

        final UserSalt createdUserSalt = this.userSaltService.create(userSalt);
        final String encryptedPassword = this.userSaltHashingService.generateHashWithSalt(signUpDTO.getPassword(), salt);

        userAccountDetails.setEncryptedPassword(encryptedPassword);
        userAccountDetails.setActive(true);

        final UserAccountDetails updatedUserAccountDetails = this.userAccountDetailsService.update(userAccountDetails);


        final UserAuthentication userAuthentication = new UserAuthentication();
        userAuthentication.setUserAccountDetails(updatedUserAccountDetails);
        userAuthentication.setUserPermissions(userPermissions);
        userAuthentication.setUserSalt(createdUserSalt);

        return userAuthentication;
    }
}
