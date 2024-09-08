package com.socialmedia.posts;

import com.socialmedia.posts.util.InitialiseDB;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.web.config.EnableSpringDataWebSupport;

@SpringBootApplication(scanBasePackages = "com.socialmedia.posts")
@EnableSpringDataWebSupport(pageSerializationMode = EnableSpringDataWebSupport.PageSerializationMode.VIA_DTO)
public class PostsApplication {

    public static void main(String[] args) {
        SpringApplication.run(PostsApplication.class, args);

    }

    @Bean(initMethod="addPosts")
    public InitialiseDB initialisePosts() {
        return new InitialiseDB();
    }

    @Bean(initMethod="createAdminAccount")
    public InitialiseDB initialiseAdminAccount() {
        return new InitialiseDB();
    }
}
