package com.giboow.boilerplate.controller;

import com.giboow.boilerplate.dto.UserSubscriptionDTO;
import com.giboow.boilerplate.entity.User;
import com.giboow.boilerplate.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@Log4j2
public class UserController {

    @Autowired
    UserService userService;

    /**
     * Add user
     *
     * @param user The user to be created
     * @return
     */
    @PostMapping(value = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public User createUser(@Validated @RequestBody User user) {
        User persist = userService.createUser(user);

        return persist;
    }


    /**
     * Return current user connected
     *
     * @param user the current Auth user
     * @return
     */
    @GetMapping(value = "/me", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    public User me(@AuthenticationPrincipal User user) {
        return user;
    }
}
