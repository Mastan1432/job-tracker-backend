package com.jobtracker.job_tracker.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jobtracker.job_tracker.entity.User;
import com.jobtracker.job_tracker.service.AuthService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return authService.register(user);
    }

    
    @PostMapping("/login")
    public User login(@RequestBody User user) {
        User loggedInUser = authService.login(user.getEmail(), user.getPassword());

        loggedInUser.setPassword(null);
        return loggedInUser;
    }
}
