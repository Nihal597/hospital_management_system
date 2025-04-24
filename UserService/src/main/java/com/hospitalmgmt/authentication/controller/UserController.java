package com.hospitalmgmt.authentication.controller;

import com.hospitalmgmt.authentication.entity.User;
import com.hospitalmgmt.authentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    UserRepository userRepository;

    @GetMapping("/users")
    public List<User> getAllUsers() {

        return userRepository.findAll();
    }
}
