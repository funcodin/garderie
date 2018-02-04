package com.garderie.service.interfaces;

import com.garderie.types.dto.SignUpDTO;
import com.garderie.types.security.auth.UserAccountDetails;
import com.garderie.types.security.auth.UserAuthentication;

public interface SignUpService {

    public UserAccountDetails signUpOwner(final SignUpDTO signUpDTO );
    public void signUpParent(final SignUpDTO signUpDTO);
    public void signUpTeacher(final SignUpDTO signUpDTO);
    public UserAuthentication signUpUserWithCode(final SignUpDTO signUpDTO);
}
