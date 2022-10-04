package com.example.MyGrowthLoginChallenge.controller;

import com.example.MyGrowthLoginChallenge.entity.User;
import com.example.MyGrowthLoginChallenge.persistence.UserRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class LoginApiController {

private UserRepository userRepository;

    public LoginApiController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/api/v1/users")
    public List<User> listUsersEndpoint() {
        List<User> listUsers = userRepository.findAll();
        return listUsers;
    }

    @GetMapping("/api/v1/users/{id}")
    public Optional<User> listUsersEndpoint(@PathVariable Long id) {
        Optional<User> userById = userRepository.findById(id);
        return userById;
    }

    @PostMapping("/api/v1/users/newUser")
    public ResponseEntity createUser(@RequestBody User user) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        userRepository.save(user);
        return ResponseEntity.ok("User created successfully for mail: "+user.getEmail());
    }


}
