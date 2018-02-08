package com.garderie.service.interfaces;

import com.garderie.types.security.auth.token.JwtTokenData;
import com.garderie.types.user.types.Teacher;
import io.jsonwebtoken.Jwt;

public interface TeacherService {

    public Teacher create(final Teacher teacher, final JwtTokenData jwtTokenData);

    public Teacher update(final Teacher teacher, final JwtTokenData jwtTokenData);

    public Teacher findTeacherById(final String id, final JwtTokenData jwtTokenData);

    public void deletedTeacherByid(final String teacherId, final JwtTokenData jwtTokenData);
}
