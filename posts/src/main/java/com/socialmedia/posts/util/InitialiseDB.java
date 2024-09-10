package com.socialmedia.posts.util;

import com.socialmedia.posts.PostsApplication;
import com.socialmedia.posts.entity.Users;
import com.socialmedia.posts.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class InitialiseDB {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostsApplication.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void createAdminAccount() {
        LOGGER.info("Creating admin account");
        Users admin = new Users();
        admin.setUsername("admin");
        String encodedPass = passwordEncoder.encode("p@123");
        admin.setPassword(encodedPass);
        userRepository.save(admin);
        LOGGER.info("Admin account created");
    }
}
