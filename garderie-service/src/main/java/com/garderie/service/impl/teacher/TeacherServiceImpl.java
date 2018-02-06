package com.garderie.service.impl.teacher;

import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.impl.account.UserAccountDetailsServiceImpl;
import com.garderie.service.interfaces.TeacherService;
import com.garderie.service.repository.TeacherRepository;
import com.garderie.types.security.auth.Authority;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.info.UserType;
import com.garderie.types.user.types.Teacher;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;

@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private UserAccountDetailsServiceImpl basicUserService;

    @Override
    public Teacher create(final Teacher teacher, final JwtTokenData jwtTokenData) {

        if (Objects.isNull(teacher)) {
            throw new ServiceException("Teacher cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(teacher.getId())) {
            throw new ServiceException("Teacher id cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(teacher.getOrgId())) {
            throw new ServiceException("Teacher must have org id", HttpStatus.BAD_REQUEST);
        }

        if(StringUtils.isBlank(teacher.getEmailId())) {
            throw new ServiceException("Teacher must have email id", HttpStatus.BAD_REQUEST);
        }


        if( !jwtTokenData.getAuthorities().contains(Authority.ROLE_OWNER) ) {
            throw new ServiceException("Teacher can be created by only owner of the org", HttpStatus.BAD_REQUEST);
        }

        if( !jwtTokenData.getOrgId().equalsIgnoreCase(teacher.getOrgId()) ){
            throw new ServiceException("Teacher can be created only by the owner of the same org", HttpStatus.FORBIDDEN);
        }

        final UserAccountDetails userAccountDetails = this.basicUserService.find(teacher.getId());
        if(Objects.isNull(userAccountDetails)) {
            throw new ServiceException("Unable to find user account for teacher", HttpStatus.BAD_REQUEST);
        }

        teacher.setUserType(UserType.TEACHER);
        teacher.setCreatedDate(new Date());
        teacher.setModifiedDate(new Date());

        return this.teacherRepository.save(teacher);
    }

    @Override
    public Teacher update(final Teacher teacher, final String teacherId, final JwtTokenData jwtTokenData) {

        if (StringUtils.isBlank(teacherId)) {
            throw new ServiceException("Teacher id cannot be empty", HttpStatus.BAD_REQUEST);
        }
        if (Objects.isNull(teacher)) {
            throw new ServiceException("Teacher cannot be empty", HttpStatus.BAD_REQUEST);
        }

        if(!teacherId.equalsIgnoreCase(jwtTokenData.getUserId())){
            throw new ServiceException("Teacher profile can be updated by profile owner", HttpStatus.BAD_REQUEST);
        }

        final Teacher existingTeacher = this.teacherRepository.findTeacherById(teacherId);

        if(StringUtils.isNotBlank(teacher.getFirstName())) {
            existingTeacher.setFirstName(teacher.getFirstName());
        }

        if(StringUtils.isNotBlank(teacher.getMiddleName())) {
            existingTeacher.setMiddleName(teacher.getMiddleName());
        }

        if(StringUtils.isNotBlank(teacher.getLastName())) {
            existingTeacher.setLastName(teacher.getLastName());
        }

        if (Objects.nonNull(teacher.getAddress())) {
            existingTeacher.setAddress(teacher.getAddress());
        }

        if (Objects.nonNull(teacher.getContactDetails())) {
            existingTeacher.setContactDetails(teacher.getContactDetails());
        }

        if (Objects.nonNull(teacher.getBirthDate())) {
            existingTeacher.setBirthDate(teacher.getBirthDate());
        }

        if (Objects.nonNull(teacher.getGender())) {
            existingTeacher.setGender(teacher.getGender());
        }

        return this.teacherRepository.save(existingTeacher);
    }

    @Override
    public Teacher findTeacherById(final String teacherId,final  JwtTokenData jwtTokenData) {
        return this.teacherRepository.findTeacherById(teacherId);
    }

}
