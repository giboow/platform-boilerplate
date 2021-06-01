package com.giboow.boilerplate.service;

import com.giboow.boilerplate.dto.UserSubscriptionDTO;
import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.entity.user.Role;
import com.giboow.boilerplate.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthServiceTests {

    @MockBean
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private AuthService authService;

    @MockBean
    private UserRepository repository;


    @Test
    public void registerUserNoExisting() {
        UserSubscriptionDTO subscriptionDTO = new UserSubscriptionDTO(
                "user@company.com",
                "password",
                "user", "basic"
        );

        when(repository.findByEmail(any(String.class))).thenReturn(Optional.empty());

        User persisted = authService.register(subscriptionDTO);
        verify(repository, times(1)).findByEmail(any(String.class));
        verify(repository, times(1)).save(any(User.class));
        verify(bCryptPasswordEncoder, times(1)).encode(any(String.class));


        assertNotNull(persisted);
        assertEquals(subscriptionDTO.getEmail(), persisted.getEmail());
        assertEquals(subscriptionDTO.getFirstname(), persisted.getFirstName());
        assertEquals(subscriptionDTO.getLastname(), persisted.getLastName());
        assertEquals(persisted.getRole(), Role.USER);
        assertTrue(persisted.isActive());
    }

}
