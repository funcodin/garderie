package com.garderie.service.impl.usersalt;

import com.garderie.service.exception.model.ServiceException;
import com.garderie.service.interfaces.UserSaltHashingService;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Service
public class UserSaltHashingServiceImpl implements UserSaltHashingService {
    @Override
    public String generateSalt() {
        try {
            final SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
            final byte[] salt = new byte[16];
            sr.nextBytes(salt);
            return encodeSalt(salt);
        } catch (NoSuchAlgorithmException nsae) {
            throw new ServiceException("Exception occured while generating secure password", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String generateHashWithSalt(String stringToHash, String salt) {
        try {
            // Create MessageDigest instance for MD5
            final MessageDigest md = MessageDigest.getInstance("MD5");
            // Add password bytes to digest
            md.update(decodeSalt(salt));
            // Get the hash's bytes
            final byte[] bytes = md.digest(stringToHash.getBytes());
            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            final StringBuilder sb = new StringBuilder();
            for (int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            // Get complete hashed password in hex format
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new ServiceException("Exception occured while generating secure password ", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    public String encodeSalt(byte[] salt) {
        return Base64.encodeBase64String(salt);
    }

    @Override
    public byte[] decodeSalt(String salt) {
        return Base64.decodeBase64(salt);
    }
}
