package com.garderie.service.interfaces;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.UserAuthentication;
import com.garderie.types.security.auth.token.JwtTokenData;

public interface SignUpService {

    public UserAccountDetails signUpOwner(final SignUpDTO signUpDTO );
    public UserAccountDetails signUpParent(final SignUpDTO signUpDTO, final String orgId);
    public UserAccountDetails signUpTeacher(final SignUpDTO signUpDTO, final JwtTokenData jwtTokenData);
    public UserAuthentication signUpUserWithCode(final SignUpDTO signUpDTO);
}
