package com.garderie.service.impl.usersalt;

import com.garderie.service.errors.GarderieErrors;
import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.UserSaltHashingService;
import com.garderie.service.interfaces.UserSaltService;
import com.garderie.service.repository.UserSaltRepository;
import com.garderie.service.validator.UserSaltValidator;
import com.garderie.types.security.auth.UserSalt;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserSaltServiceImpl implements UserSaltService {

    private static final Logger log = LoggerFactory.getLogger(UserSaltService.class);

    @Autowired
    UserSaltValidator userSaltValidator;

    @Autowired
    UserSaltRepository userSaltRepository;

    @Override
    public UserSalt create(final UserSalt userSalt) {
        final List<GarderieErrors> validationErrors = userSaltValidator.validatePreSave(userSalt);
        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new ServiceException(validationErrors.toString(), HttpStatus.BAD_REQUEST);
        }
        return this.userSaltRepository.save(userSalt);
    }

    @Override
    public UserSalt update(final UserSalt userSalt) {
        final List<GarderieErrors> validationErrors = this.userSaltValidator.validatePreSave(userSalt);
        if (CollectionUtils.isNotEmpty(validationErrors)) {
            throw new ServiceException(validationErrors.toString(), HttpStatus.BAD_REQUEST);
        }
        return this.userSaltRepository.save(userSalt);
    }

    @Override
    public UserSalt findByEmailId(final String emailId) {
        if (StringUtils.isBlank(emailId)) {
            throw new ServiceException("Email Id is required", HttpStatus.BAD_REQUEST);
        }
        return this.userSaltRepository.findByEmailId(emailId);
    }

    @Override
    public void deleteByEmailId(final String emailId) {

        if (StringUtils.isBlank(emailId)) {
            throw new ServiceException("Email Id is required", HttpStatus.BAD_REQUEST);
        }
        this.userSaltRepository.deleteByEmailId(emailId);
    }
}
