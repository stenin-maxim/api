package com.example.api.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.api.dto.UserDto;
import com.example.api.entity.User;
import com.example.api.mapper.UserMapper;
import com.example.api.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/user", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserController {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getAdById(@PathVariable long id) {
        User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Not found Ad with id = " + id));
        return new ResponseEntity<>(userMapper.toDto(user), HttpStatus.OK);
    }
}
