package com.giboow.boilerplate.service;


import com.giboow.boilerplate.config.AppSercurityConfig;
import com.giboow.boilerplate.dto.UserSubscriptionDTO;
import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.entity.user.Role;
import com.giboow.boilerplate.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Log4j2
public class UserService {

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    UserRepository userRepository;

    /**
     * Create user and then return the persisted object
     *
     * @param user User to be created
     * @return User persited
     */
    @Transactional
    public User createUser(User user) {

        userRepository.save(user);
        log.info("createUser : {} created with id {} ", user.getEmail(), user.getId());
        return user;
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
        if(userOptional.isPresent()) {
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
        return createUser(user);
    }
}
