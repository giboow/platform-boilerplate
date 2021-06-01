package com.giboow.boilerplate.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.giboow.boilerplate.config.AppSercurityConfig;
import com.giboow.boilerplate.config.SpringSecurityWebAuxTestConfig;
import com.giboow.boilerplate.dto.UserLoginDTO;
import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.security.WebSecurity;
import com.giboow.boilerplate.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import({SpringSecurityWebAuxTestConfig.class, WebSecurity.class, AppSercurityConfig.class})
@ActiveProfiles("test")
public class AuthControllerWebTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    private final ObjectMapper objectMapper = new ObjectMapper();


    @Test
    public void testSignin() throws Exception {

        UserLoginDTO userLoginDTO = new UserLoginDTO("admin@company.com", "monPassword");
        User userSigned = new User();
        when(authService.signin(any(String.class), any(String.class))).thenReturn(userSigned);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/auth/signIn")
                        .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(userLoginDTO))
        ).andExpect(status().isOk());

    }
}
