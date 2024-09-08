package com.socialmedia.posts.dao;

import com.socialmedia.posts.entity.Users;
import com.socialmedia.posts.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UsesrDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(UsesrDao.class);
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    public Users createUser(Users user) {
        LOGGER.info("Saving user data");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public List<Users> getAllUsers() {
        return userRepository.findAll();
    }

    public boolean isUserRegistered(String username) {
        return userRepository.findByUsername(username) == null ? false: true;
    }
}