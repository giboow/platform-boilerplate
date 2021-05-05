package com.giboow.boilerplate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giboow.boilerplate.config.AppSercurityConfig;
import com.giboow.boilerplate.config.SpringSecurityWebAuxTestConfig;
import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.entity.user.Role;
import com.giboow.boilerplate.security.WebSecurity;
import com.giboow.boilerplate.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.security.web.header.Header;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@WebMvcTest
//@Import(SpringSecurityWebAuxTestConfig.class)
@Import({SpringSecurityWebAuxTestConfig.class, WebSecurity.class, AppSercurityConfig.class})
public class UserControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private UserService userService;

    @Test
    @WithUserDetails("admin@company.com")
    public void testAddUser() throws Exception {
        User newUser = new User(
                1l, "user@company.com",
                "password", Role.USER,
                "user", "basic", true
        );
        when(userService.createUser(newUser)).thenReturn(newUser);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/user")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(newUser))
        ).andExpect(status().isOk());

        Mockito.verify(userService, times(1)).createUser(any(User.class));
    }


    @Test
    public void testMeForbidenAccess() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/user/me"))
                .andExpect(status().isForbidden());
    }

    @Test
    @WithUserDetails("user@company.com")
    public void testMeSuccess() throws Exception {

        User user = new User(
                1l, "user@company.com",
                "password", Role.USER,
                "user", "basic", true
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/user/me").with(user(user)))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content().string(objectMapper.writeValueAsString(user)));
    }
}
