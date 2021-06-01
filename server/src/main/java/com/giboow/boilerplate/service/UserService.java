package com.giboow.boilerplate.service;


import com.giboow.boilerplate.dto.UserLoginDTO;
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
     * Signin uses : Get the user and then compare the password
     * If User is not found or the email provided doesn't match , this method return nul
     * else return the User object
     * @param login
     */
    public User signIn(UserLoginDTO login) {
        User user = null;

        Optional<User> optionalUser = userRepository.findByEmail(login.getEmail());

        if (optionalUser.isPresent()) {
            User userToTest = optionalUser.get();

            if (bCryptPasswordEncoder.matches(login.getPassword(), user.getPassword())) {
                user = userToTest;

            } else {
                log.debug("Bad password for user " + user.getEmail());
            }
        } else {
            log.debug("User " + user.getEmail() + " not found");
        }

        return user;
    }
}
