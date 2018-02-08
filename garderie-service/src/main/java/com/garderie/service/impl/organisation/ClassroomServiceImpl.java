package com.garderie.service.impl.organisation;

import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.ClassroomService;
import com.garderie.service.repository.organisation.ClassroomRepository;
import com.garderie.service.util.ControllerUtil;
import com.garderie.types.org.Classroom;
import com.garderie.types.security.auth.token.JwtTokenData;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomRepository classroomRepository;

    @Override
    public Classroom create(final Classroom classroom) {
        if (Objects.isNull(classroom) || StringUtils.isBlank(classroom.getOrgId())) {
            throw new ServiceException("Org id is required to create class room", HttpStatus.BAD_REQUEST);
        }

        if (StringUtils.isBlank(classroom.getName())) {
            throw new ServiceException("Classroom name is required to create class room", HttpStatus.BAD_REQUEST);
        }

        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();

        if (!jwtTokenData.getOrgId().equals(classroom.getOrgId())) {
            throw new ServiceException("Cannot create class room in another org", HttpStatus.UNAUTHORIZED);
        }

        classroom.setId(UUID.randomUUID().toString());
        classroom.setCreatedDate(new Date());
        classroom.setModifiedDate(new Date());

        final Classroom createdClassroom = this.classroomRepository.save(classroom);

        return createdClassroom;
    }

    @Override
    public Classroom update(final Classroom classroom) {
        if (Objects.isNull(classroom) || StringUtils.isBlank(classroom.getOrgId())) {
            throw new ServiceException("Org id is required to update class room", HttpStatus.BAD_REQUEST);
        }

        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();

        if (!jwtTokenData.getOrgId().equals(classroom.getOrgId())) {
            throw new ServiceException("Cannot update class room in another org", HttpStatus.UNAUTHORIZED);
        }

        final Classroom existingClassroom = this.classroomRepository.findOne(classroom.getOrgId());

        if (Objects.isNull(existingClassroom)) {
            throw new ServiceException("Unable to find class room", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        if (StringUtils.isNotBlank(classroom.getName())) {
            existingClassroom.setName(classroom.getName());
        }
        existingClassroom.setModifiedDate(new Date());

        final Classroom updatedClassroom = this.classroomRepository.save(classroom);

        return updatedClassroom;
    }


    @Override
    public Classroom addStudentsToClassroom(final Classroom classroom) {
        if (Objects.isNull(classroom)) {
            throw new ServiceException("Empty request", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(classroom.getOrgId())) {
            throw new ServiceException("Org id is required", HttpStatus.BAD_REQUEST);
        }

        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();

        if (!jwtTokenData.getOrgId().equals(classroom.getOrgId())) {
            throw new ServiceException("Cannot add students to class room in another org", HttpStatus.UNAUTHORIZED);
        }

        if (CollectionUtils.isEmpty(classroom.getStudentIds())) {
            throw new ServiceException("Student id is required", HttpStatus.BAD_REQUEST);
        }

        final Classroom existingClassRoom = this.classroomRepository.findOne(classroom.getId());

        existingClassRoom.getStudentIds().addAll(classroom.getStudentIds());
        existingClassRoom.setModifiedDate(new Date());

        final Classroom updatedClassroom = this.classroomRepository.save(existingClassRoom);
        return updatedClassroom;
    }

    @Override
    public Classroom removeStudentsFromClassroom(final Classroom classroom) {
        if (Objects.isNull(classroom)) {
            throw new ServiceException("Empty request", HttpStatus.BAD_REQUEST);
        }
        if (StringUtils.isBlank(classroom.getOrgId())) {
            throw new ServiceException("Org id is required", HttpStatus.BAD_REQUEST);
        }

        if (CollectionUtils.isEmpty(classroom.getStudentIds())) {
            throw new ServiceException("Student id is required", HttpStatus.BAD_REQUEST);
        }

        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        if (jwtTokenData.getOrgId().equals(classroom.getOrgId())) {
            throw new ServiceException("Cannot add students to class room in another org", HttpStatus.UNAUTHORIZED);
        }

        final Classroom existingClassRoom = this.classroomRepository.findOne(classroom.getId());
        existingClassRoom.getStudentIds().removeAll(classroom.getStudentIds());
        existingClassRoom.setModifiedDate(new Date());

        final Classroom updatedClassroom = this.classroomRepository.save(existingClassRoom);
        return updatedClassroom;
    }


    @Override
    public Set<Classroom> findAllClassroomsByOrgId(final String orgId) {
        if (StringUtils.isBlank(orgId)) {
            throw new ServiceException("Org id is required to find classrooms", HttpStatus.BAD_REQUEST);
        }

        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        if (jwtTokenData.getOrgId().equals(orgId)) {
            throw new ServiceException("Cannot add students to class room in another org", HttpStatus.UNAUTHORIZED);
        }
        return this.classroomRepository.findClassroomByOrgId(orgId);
    }

    @Override
    public void deleteById(final String classroomId) {
        if (StringUtils.isBlank(classroomId)) {
            throw new ServiceException("Classroom id is required", HttpStatus.BAD_REQUEST);
        }

        final Classroom existingClassroom = this.classroomRepository.findOne(classroomId);



        final JwtTokenData jwtTokenData = ControllerUtil.getTokenDataFromHttpRequest();
        if (jwtTokenData.getOrgId().equals(existingClassroom.getOrgId())) {
            throw new ServiceException("Cannot delete classroom in another org", HttpStatus.UNAUTHORIZED);
        }
        this.classroomRepository.delete(classroomId);
    }
}
