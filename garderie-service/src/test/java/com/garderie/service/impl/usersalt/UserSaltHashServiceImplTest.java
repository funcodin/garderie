package com.garderie.service.impl.usersalt;

import org.junit.Before;
import org.junit.Test;

public class UserSaltHashServiceImplTest {

   private UserSaltHashingServiceImpl userSaltHashingService;

    @Before
    public void setUp(){
       this.userSaltHashingService = new UserSaltHashingServiceImpl();
    }

    @Test
    public void testGenerateHashWithSalt(){

        final String password = "password";

        final String salt = this.userSaltHashingService.generateSalt();

        final String passwordHash = this.userSaltHashingService.generateHashWithSalt(password,salt);

        System.out.println(passwordHash);
        final String salt1 = this.userSaltHashingService.generateSalt();

        final String passwordHash1 = this.userSaltHashingService.generateHashWithSalt(password,salt1);

        System.out.println(passwordHash1);



    }

}
