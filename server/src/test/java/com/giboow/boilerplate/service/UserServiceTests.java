package com.giboow.boilerplate.service;

import com.giboow.boilerplate.config.AppSercurityConfig;
import com.giboow.boilerplate.config.SpringSecurityWebAuxTestConfig;
import com.giboow.boilerplate.dto.UserSubscriptionDTO;
import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.entity.user.Role;
import com.giboow.boilerplate.repository.UserRepository;
import com.giboow.boilerplate.security.WebSecurity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.extension.Extensions;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.springframework.amqp.rabbit.junit.JUnitUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository repository;


    @Test
    public void createUserTest() {
        User newUser = new User(
                1l, "user@company.com",
                "password", Role.USER,
                "user", "basic", true
        );

        when(repository.save(newUser)).thenReturn(newUser);
        User persisted = userService.createUser(newUser);

        assertEquals(newUser, persisted);
    }
}
