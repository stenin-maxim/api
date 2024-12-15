package com.example.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.model.User;
import com.example.api.repository.UserRepository;


@RestController
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    @Autowired
    UserRepository userRepository;

    @GetMapping("/{id}")
    public ResponseEntity<User> getAdById(@PathVariable long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found Ad with id = " + id));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }
}
