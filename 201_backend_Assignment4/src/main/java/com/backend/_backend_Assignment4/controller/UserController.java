package com.backend._backend_Assignment4.controller;


import com.backend._backend_Assignment4.dto.LoginRequestDTO;
import com.backend._backend_Assignment4.dto.UserRegistrationDTO;
import com.backend._backend_Assignment4.model.User;
import com.backend._backend_Assignment4.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping(path ="api/user")
public class UserController {

    @Autowired
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> getUsers(){
        return userService.getUsers();

    }


    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserRegistrationDTO userregistration) {
        User newUser = userService.singup(userregistration);
        if (newUser != null) {
            return ResponseEntity.ok(Map.of("message", "User registered successfully", "userId", newUser.getId()));

        } else {
            return ResponseEntity.badRequest().body("User registration failed");
        }
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequestDTO loginRequest) {
        User user = userService.login(loginRequest);
        if (user != null) {
            // Create a response object that includes the userId
            Map<String, Object> response = new HashMap<>();
            response.put("message", "User logged in successfully");
            response.put("userId", user.getId()); // Replace getId() with the method name that returns the user's ID
            System.out.println(user.getId());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }



}
