package com.kodilla.ecommercee.controller;

import com.kodilla.ecommercee.domain.dto.UserDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("v1/user")
public class UserController {
    @GetMapping
    public List<UserDto> getUsers() {
        return new ArrayList<>();
    }

    @GetMapping(value = "{userId}")
    public UserDto getUser(@PathVariable long userId) {
        return new UserDto();
    }

    @DeleteMapping
    public void deleteUser(@PathVariable long userId) {
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public UserDto updateUser(@RequestBody UserDto userDto) {
        return new UserDto();
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto) {
    }
}

