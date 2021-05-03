package com.giboow.boilerplate.controller;

import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.giboow.boilerplate.repository.UserRepository;

import javax.swing.text.html.parser.Entity;

@RestController
@RequestMapping("/user")
public class UserController {


    @Autowired
    UserService userService;

    /**
     * Return current user connected
     * @param user
     * @return
     */
    @GetMapping("/me")
    public User me(@AuthenticationPrincipal User user) {
        return user;
    }

    /**
     * Add user
     * @param user
     * @return
     */
    @PostMapping("")
    @ResponseBody
    public User createUser(@Validated  @RequestBody User user) {
        return userService.createUser(user);
    }
}
