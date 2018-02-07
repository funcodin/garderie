package com.garderie.service.impl.orgowner;

import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.impl.account.UserAccountDetailsServiceImpl;
import com.garderie.service.interfaces.OrgOwnerService;
import com.garderie.service.repository.OrgOwnerRepository;
import com.garderie.types.org.OrgOwner;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.info.UserType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class OrgOwnerServiceImpl implements OrgOwnerService {

    @Autowired
    private OrgOwnerRepository orgOwnerRepository;

    @Autowired
    private UserAccountDetailsServiceImpl userAccountDetailsService;

    @Override
    public OrgOwner create(final OrgOwner orgOwner, final JwtTokenData jwtTokenData) {

        if (Objects.isNull(orgOwner)) {
            throw new ServiceException("OrgOwner cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(orgOwner.getId())) {
            throw new ServiceException("Orgowner id cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(orgOwner.getEmailId())) {
            throw new ServiceException("Orgowner must have email id", HttpStatus.BAD_REQUEST);
        }

        if (!jwtTokenData.getAuthorities().contains(Authority.ROLE_OWNER)) {
            throw new ServiceException("Owner can be created by only owner of the org", HttpStatus.BAD_REQUEST);
        }

        final UserAccountDetails userAccountDetails = this.userAccountDetailsService.find(orgOwner.getId());

        if (Objects.isNull(userAccountDetails)) {
            throw new ServiceException("Unable to find user account for org owner", HttpStatus.BAD_REQUEST);
        }

        orgOwner.setUserType(UserType.OWNER);
        orgOwner.setCreatedDate(new Date());
        orgOwner.setModifiedDate(new Date());

        return this.orgOwnerRepository.save(orgOwner);
    }

    //This call should be made really carefully as we dont use auth here
    @Override
    public OrgOwner createOrgOwnerInternally(final OrgOwner orgOwner) {
        if (Objects.isNull(orgOwner)) {
            throw new ServiceException("OrgOwner cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(orgOwner.getId())) {
            throw new ServiceException("Orgowner id cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(orgOwner.getEmailId())) {
            throw new ServiceException("Orgowner must have email id", HttpStatus.BAD_REQUEST);
        }

        final UserAccountDetails userAccountDetails = this.userAccountDetailsService.find(orgOwner.getId());

        if (Objects.isNull(userAccountDetails)) {
            throw new ServiceException("Unable to find user account for org owner", HttpStatus.BAD_REQUEST);
        }

        orgOwner.setUserType(UserType.OWNER);
        orgOwner.setCreatedDate(new Date());
        orgOwner.setModifiedDate(new Date());

        return this.orgOwnerRepository.save(orgOwner);
    }

    //This call should be made really carefully as we dont use auth here
    @Override
    public OrgOwner updateOrgOwnerOrgIdInternally(final String orgownerId, final String orgId) {

        if (StringUtils.isBlank(orgownerId)) {
            throw new ServiceException("Org owner id cannot be empty", HttpStatus.BAD_REQUEST);
        }

        final OrgOwner existingOrgOwner = this.orgOwnerRepository.findOne(orgownerId);

        if(Objects.isNull(existingOrgOwner)) {
            throw new ServiceException("Unable to find org owner ", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        existingOrgOwner.setOrgId(orgId);

        return this.orgOwnerRepository.save(existingOrgOwner);
    }

    @Override
    public OrgOwner update(OrgOwner orgOwner, JwtTokenData jwtTokenData) {

        if (StringUtils.isBlank(orgOwner.getId())) {
            throw new ServiceException("Org owner id cannot be empty", HttpStatus.BAD_REQUEST);
        }

        final OrgOwner existingOrgOwner = this.orgOwnerRepository.findOne(orgOwner.getId());

        if(Objects.isNull(existingOrgOwner)) {
            throw new ServiceException("Unable to find org owner ", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (!existingOrgOwner.getId().equals(jwtTokenData.getUserId())) {
            throw new ServiceException("Only the org owner can update itself");
        }

        if (StringUtils.isNotBlank(orgOwner.getFirstName())) {
            existingOrgOwner.setFirstName(orgOwner.getFirstName());
        }

        if (StringUtils.isNotBlank(orgOwner.getMiddleName())) {
            existingOrgOwner.setMiddleName(orgOwner.getMiddleName());
        }

        if (StringUtils.isNotBlank(orgOwner.getLastName())) {
            existingOrgOwner.setLastName(orgOwner.getLastName());
        }


        /*
        We dont need deep object check as UI will send existing as well as update info
        if (Objects.nonNull(orgOwner.getContactDetails())) {
            final ContactDetails contactDetails = orgOwner.getContactDetails();
            if (StringUtils.isNotBlank(contactDetails.getHomePhoneNumber())) {
                existingOrgOwner.getContactDetails().setHomePhoneNumber(contactDetails.getHomePhoneNumber());
            }

            if (StringUtils.isNotBlank(contactDetails.getWorkPhoneNumber())) {
                existingOrgOwner.getContactDetails().setWorkPhoneNumber(contactDetails.getWorkPhoneNumber());
            }

            if (CollectionUtils.isNotEmpty(contactDetails.getCellPhoneNumbers())) {
                if (CollectionUtils.isEmpty(existingOrgOwner.getContactDetails().getCellPhoneNumbers())) {
                    existingOrgOwner.getContactDetails().setCellPhoneNumbers(contactDetails.getCellPhoneNumbers());
                } else {
                    existingOrgOwner.getContactDetails().getCellPhoneNumbers().addAll(contactDetails.getCellPhoneNumbers());
                }
            }

            existingOrgOwner.setContactDetails(orgOwner.getContactDetails());
        }
        */

        if (Objects.nonNull(orgOwner.getContactDetails())) {
            existingOrgOwner.setContactDetails(orgOwner.getContactDetails());
        }

        if (Objects.nonNull(orgOwner.getAddress())) {
            existingOrgOwner.setAddress(orgOwner.getAddress());
        }

        if (Objects.nonNull(orgOwner.getBirthDate())) {
            existingOrgOwner.setBirthDate(orgOwner.getBirthDate());
        }

        if (Objects.nonNull(orgOwner.getGender())) {
            existingOrgOwner.setGender(orgOwner.getGender());
        }

        existingOrgOwner.setModifiedDate(new Date());
        return this.orgOwnerRepository.save(orgOwner);
    }

    @Override
    public OrgOwner findByEmailId(String emailId) {
        return this.orgOwnerRepository.findByEmailId(emailId);
    }

    @Override
    public OrgOwner findByOrgOwnerId(String id) {
        return this.orgOwnerRepository.findOne(id);
    }

    @Override
    public void deleteByOrgOwnerId(String id) {
        this.orgOwnerRepository.delete(id);
    }
}
