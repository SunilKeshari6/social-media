package com.socialmedia.posts;

import com.socialmedia.posts.controller.PostsController;
import com.socialmedia.posts.controller.UserController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class PostsApplicationTests {

    @Autowired
    PostsController postsController;

    @Autowired
    UserController userController;

    @Test
    void contextLoads() {
        assertThat(postsController).isNotNull();
        assertThat(userController).isNotNull();
    }

}
