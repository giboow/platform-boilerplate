package com.giboow.boilerplate.controller;

import com.giboow.boilerplate.dto.UserLoginDTO;
import com.giboow.boilerplate.dto.UserSubscriptionDTO;
import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.service.AuthService;
import com.giboow.boilerplate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthService authService;

    /**
     * User subcription
     *
     * @param subscription
     * @return
     */
    @PostMapping(value = "signup", consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User signUp(@Validated @RequestBody UserSubscriptionDTO subscription) {

        User user = authService.register(subscription);
        return user;

    }

    /**
     * User login
     */
    @PostMapping(value = "signIn")
    @ResponseBody
    public User signIn(@Validated @RequestBody UserLoginDTO login) throws ResponseStatusException {

        User user = authService.signin(login.getEmail(), login.getPassword());
        if (user != null) {
            throw new ResponseStatusException(
                    HttpStatus.UNAUTHORIZED
            );
        } else {
            return user;
        }
    }
}
