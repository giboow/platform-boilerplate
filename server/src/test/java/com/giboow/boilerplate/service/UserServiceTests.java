package com.giboow.boilerplate.service;

import com.giboow.boilerplate.dto.UserSubscriptionDTO;
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

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private UserService userService;

    @MockBean
    private AuthService authService;

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

    @Test
    public void registerUserNoExisting() {
        UserSubscriptionDTO subscriptionDTO = new UserSubscriptionDTO(
                "user@company.com",
                "password",
                "user", "basic"
        );

        when(repository.finByEmail(any(String.class))).thenReturn(Optional.empty());

        User persisted = userService.register(subscriptionDTO);
        verify(repository, times(1)).finByEmail(any(String.class));
        verify(repository, times(1)).save(any(User.class));

        assertNotNull(persisted);
        assertEquals(subscriptionDTO.getEmail(), persisted.getEmail());
        assertEquals(subscriptionDTO.getFirstname(), persisted.getFirstName());
        assertEquals(subscriptionDTO.getLastname(), persisted.getLastName());
        assertTrue(bCryptPasswordEncoder.matches(subscriptionDTO.getPassword(), persisted.getPassword()));
        assertEquals(persisted.getRole(), Role.USER);
        assertTrue(persisted.isActive());
    }

}
