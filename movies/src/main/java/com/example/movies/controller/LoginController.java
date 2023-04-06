package com.example.movies.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.movies.model.User;

@RestController
@RequestMapping("/auth")
@CrossOrigin(maxAge = 3600)
public class LoginController {
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        // Check if the username and password are correct
        if (user.getUsername().equals("root") && user.getPassword().equals("root")) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}

