package com.giboow.boilerplate.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.giboow.boilerplate.config.AppSercurityConfig;
import com.giboow.boilerplate.dto.UserSubscriptionDTO;
import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.entity.user.Role;
import com.giboow.boilerplate.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Service
@Log4j2
public class AuthService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    AppSercurityConfig sercurityConfig;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    /**
     * Get Authentication object from username / password
     *
     * @param username
     * @param password
     * @return
     */
    public Authentication authenticate(String username, String password) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password));

        return authentication;
    }

    /**
     * Signin user : authenticate, then get the user
     *
     * @param email
     * @param password
     * @return
     */
    public User signin(String email, String password) {

        Authentication authentication = authenticate(email, password);
        User user = (User) authentication.getPrincipal();

        return user;
    }

    /**
     * Generate a token from userdetail
     *
     * @param userDetails
     * @return
     */
    public String generateToken(UserDetails userDetails) {
        String jwt = JWT.create().withSubject(userDetails.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + (sercurityConfig.getExpirationTime() * 1000)))
                .sign(Algorithm.HMAC512(sercurityConfig.getSecret().getBytes()));

        return jwt;
    }

    /**
     * Register an user from register route
     * Create a user model from subscription DTO, then save it and return the User model
     *
     * @param subscription
     */
    @Transactional
    public User register(UserSubscriptionDTO subscription) {
        // Encode password
        String passwordHash = bCryptPasswordEncoder.encode(subscription.getPassword());

        // Test if there is another user with same password...
        // If the user exists and password are equals, then connect
        Optional<User> userOptional = userRepository.findByEmail(subscription.getEmail());
        if (userOptional.isPresent()) {
            User userExist = userOptional.get();
            if (userExist.getPassword().equals(passwordHash)) {
                return userExist;
            }
        }

        // Default role
        Role role = Role.USER;
        User user = new User(
                null, subscription.getEmail(), passwordHash,
                role, subscription.getFirstname(), subscription.getLastname(), true
        );

        // Use create user method
        return userService.createUser(user);
    }
}
