package com.pdev.spacetime.controller;

import com.pdev.spacetime.entity.User;
import com.pdev.spacetime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/{id}")
    public User findDetailedUser(@PathVariable Long id) {
        User user = userService.findDetailedUser(id);
        return user;
    }

}
