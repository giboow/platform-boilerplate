package com.giboow.boilerplate.service;

import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.entity.user.Role;
import com.giboow.boilerplate.repository.UserRepository;
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
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class UserServiceTests {

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

        Assertions.assertEquals(newUser, persisted);
    }

}
