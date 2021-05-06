package com.giboow.boilerplate.service;


import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    /**
     * Create user and then return the persisted object
     * @param user User to be created
     * @return User persited
     */
    @Transactional
    public User createUser(User user) {
        return userRepository.save(user);
    }
}
