package com.freshvotes.service;


import com.freshvotes.security.WebSecurityConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;

public class UserDetailsServiceTest {
    WebSecurityConfiguration webSecurityConfiguration = new WebSecurityConfiguration();
    @Test
    public void generate_encrypted_password() {
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "passwordxm";
        String encodedPassword = encoder.encode(rawPassword);

        System.out.println(encodedPassword);
        assertNotSame(rawPassword,encodedPassword);
    }

//    @Test
//    public void isClassOfUserDetailsServiceImpl() {
//
//        assertInstanceOf(UserDetailsService.class,webSecurityConfiguration.userDetailsService());
//    }
}