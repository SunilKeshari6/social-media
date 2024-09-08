package com.socialmedia.posts.util;

import com.socialmedia.posts.PostsApplication;
import com.socialmedia.posts.entity.PostEntity;
import com.socialmedia.posts.entity.Users;
import com.socialmedia.posts.repository.PostsRepository;
import com.socialmedia.posts.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import java.util.Date;

public class InitialiseDB {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostsApplication.class);
    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

   public void  addPosts() {
        Integer max_posts = 1000000; // 1 million posts
        LOGGER.info("Saving {} posts to DB", max_posts);
        for(long i = 1; i<=max_posts; i++){
            PostEntity postEntity = new PostEntity();
            postEntity.setUsername("user-1");
            postEntity.setContent("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur? At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere");
            postEntity.setCreatedDate(new Date());
            postEntity.setModifiedDate(new Date());
            postsRepository.save(postEntity);
        }
       LOGGER.info("Added {} posts to DB", max_posts);
    }

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
