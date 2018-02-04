package com.garderie.service.interfaces;

public interface UserSaltHashingService {

    public String generateSalt();
    public String generateHashWithSalt(final String password, final String salt);
    public String encodeSalt( final byte[] salt);
    public byte[] decodeSalt( final String salt );

}
