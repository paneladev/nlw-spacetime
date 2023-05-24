package com.pdev.spacetime.controller;

import com.pdev.spacetime.controller.request.AuthRequest;
import com.pdev.spacetime.controller.response.UserRegisterResponse;
import com.pdev.spacetime.entity.User;
import com.pdev.spacetime.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<UserRegisterResponse> registerUser(@RequestBody AuthRequest request) {
        return userService.registerUser(request)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{id}")
    public User findDetailedUser(@PathVariable Long id) {
        return userService.findDetailedUser(id);
    }

}
