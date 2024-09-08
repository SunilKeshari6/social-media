package com.socialmedia.posts.controller;

import com.socialmedia.posts.entity.Users;
import com.socialmedia.posts.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Users user) {
        if(!ObjectUtils.isEmpty(userService.createUser(user)))
            return new ResponseEntity("User Registration successful", HttpStatus.OK);
        else
            throw new RuntimeException("User Registration successful");
    }

    @GetMapping("/all")
    public ResponseEntity<List<Users>> getAllRegisteredUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

}