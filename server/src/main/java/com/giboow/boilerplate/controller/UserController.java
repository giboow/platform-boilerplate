package com.giboow.boilerplate.controller;

import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.service.UserService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Return current user connected
     *
     * @param user the current Auth user
     * @return
     */
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User me(@AuthenticationPrincipal User user) {
        return user;
    }

    /**
     * Add user
     *
     * @param user The user to be created
     * @return
     */
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public User createUser(@Validated @RequestBody User user) {
        User persist = userService.createUser(user);

        return persist;
    }

}
