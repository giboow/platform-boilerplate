package com.giboow.boilerplate.config;

import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.entity.user.Role;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import java.util.Arrays;

@TestConfiguration
public class SpringSecurityWebAuxTestConfig {

    @Bean
    @Primary
    public UserDetailsService userDetailsService() {
        User basicUser = new User(
                1l, "user@company.com", "password",
                Role.USER, "user", "basic", true
        );

        User adminUser = new User(
                2l, "admin@company.com", "passwordAdmin",
                Role.ADMIN, "user", "admin", true
        );

        return new InMemoryUserDetailsManager(Arrays.asList(
                basicUser, adminUser
        ));
    }
}
