package com.garderie.service.interfaces;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.UserAuthentication;

public interface SignUpService {

    public UserAccountDetails signUpOwner(final SignUpDTO signUpDTO );
    public UserAccountDetails signUpParent(final SignUpDTO signUpDTO, final String orgId);
    public UserAccountDetails signUpTeacher(final SignUpDTO signUpDTO, final String orgId);
    public UserAuthentication signUpUserWithCode(final SignUpDTO signUpDTO);
}
