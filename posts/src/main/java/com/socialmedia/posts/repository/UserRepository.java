package com.socialmedia.posts.repository;

import com.socialmedia.posts.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

    Users findByUsername(String userName);
}
