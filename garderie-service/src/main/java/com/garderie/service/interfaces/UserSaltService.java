package com.garderie.service.interfaces;

import com.garderie.types.security.auth.UserSalt;

public interface UserSaltService {

    public UserSalt create( final UserSalt userSalt );
    public UserSalt update(final UserSalt userSalt);
    public UserSalt findByEmailId( final String emailId);
    public void deleteByEmailId(final String emailId);
}
