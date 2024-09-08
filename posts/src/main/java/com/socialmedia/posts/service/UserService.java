package com.socialmedia.posts.service;

import com.socialmedia.posts.dao.UsesrDao;
import com.socialmedia.posts.entity.Users;
import com.socialmedia.posts.exception.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);
    @Autowired
    private UsesrDao usesrDao;

    public Users createUser(Users user) {
        LOGGER.info("Creating user with username: {}", user.getUsername());
        if(usesrDao.isUserRegistered(user.getUsername())) {
            LOGGER.error("User already registered");
            throw new ServiceException("User already registered.");
        }
        LOGGER.info("User: {} registered successfully.", user.getUsername());
        return usesrDao.createUser(user);
    }

    public List<Users> getAllUsers() {
        return usesrDao.getAllUsers();
    }
}