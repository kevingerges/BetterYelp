package com.backend._backend_Assignment4.service;

import com.backend._backend_Assignment4.dto.LoginRequestDTO;
import com.backend._backend_Assignment4.dto.UserRegistrationDTO;
import com.backend._backend_Assignment4.model.User;
import com.backend._backend_Assignment4.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;


@Service // or @Component
public class UserService  {
    @Autowired
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }


    public User login(LoginRequestDTO loginRequest) {
        return userRepository.findByUsernameAndPassword(loginRequest.getUsername(), loginRequest.getPassword());
    }


    public User singup(UserRegistrationDTO userregistration) {
        // Check if a user with the given email already exists
        Optional<User> existingUser = userRepository.findByEmail(userregistration.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }

        User newUser = new User();
        newUser.setUsername(userregistration.getUsername());
        newUser.setEmail(userregistration.getEmail());
        newUser.setPassword(userregistration.getPassword());

        // Save the new user to the database
        return userRepository.save(newUser);
    }



}

