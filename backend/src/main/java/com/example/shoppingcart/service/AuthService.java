package com.example.shoppingcart.service;

import com.example.shoppingcart.model.User;
import com.example.shoppingcart.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository repo;

    // returns false if the username is already taken
    public boolean register(User user) {
        if (repo.findByUsername(user.getUsername()) != null) {
            return false;
        }
        repo.save(user);
        return true;
    }

    public boolean login(User user) {
        User found = repo.findByUsername(user.getUsername());
        return found != null && found.getPassword().equals(user.getPassword());
    }
}
