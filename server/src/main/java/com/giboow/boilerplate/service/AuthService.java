package com.giboow.boilerplate.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.giboow.boilerplate.config.AppSercurityConfig;
import com.giboow.boilerplate.entity.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
@Log4j2
public class AuthService {

    @Autowired
    AppSercurityConfig sercurityConfig;

    @Autowired
    AuthenticationManager authenticationManager;

    /**
     * Get Authentication object from username / password
     * @param username
     * @param password
     * @return
     */
    public  Authentication authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return authentication;
    }

    /**
     * Signin user : authenticate, then get the user
     * @param username
     * @param password
     * @return
     */
    public User signin(String username, String password){

        Authentication authentication = authenticate(username, password);
        User user = (User) authentication.getPrincipal();

        return user;
    }

    /**
     * Generate a token from userdetail
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        String jwt = JWT.create().withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (sercurityConfig.getExpirationTime() * 1000)))
                .sign(Algorithm.HMAC512(sercurityConfig.getSecret().getBytes()));

        return jwt;
    }
}
