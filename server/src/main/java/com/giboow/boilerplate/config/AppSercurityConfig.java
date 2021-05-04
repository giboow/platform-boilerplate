package com.giboow.boilerplate.config;

import lombok.Data;
import org.hibernate.validator.constraints.Length;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Configuration
@ConfigurationProperties(prefix = "app.security")
@Data
public class AppSercurityConfig {

    /**
     * Signup Url : create user url
     */
    protected String signUpUrl;

    /**
     * SignIn url : login url
     */
    @NotBlank
    protected String signInUrl;

    /**
     * Expiration time in seconds
     */
    @NotBlank
    @Min(0)
    protected Integer expirationTime = 900;

    /**
     * Secret use for hash
     */
    @NotBlank
    @Length(min = 8)
    protected String secret;

    @NotBlank
    protected String headerString = "Authorization";

    @NotBlank
    protected String tokenPrefix = "Bearer ";


    @Bean("customBCryptPasswordEncoder")
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
