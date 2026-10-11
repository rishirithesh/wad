package com.example.shoppingcart.controller;

import com.example.shoppingcart.model.User;
import com.example.shoppingcart.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user) {
        if (service.register(user)) {
            return ResponseEntity.ok("Registered successfully");
        }
        return ResponseEntity.badRequest().body("Username already exists");
    }

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (service.login(user)) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid username or password");
    }
}
