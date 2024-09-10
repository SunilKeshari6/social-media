package com.socialmedia.posts.service;

import com.socialmedia.posts.dao.PostsDAO;
import com.socialmedia.posts.entity.PostEntity;
import com.socialmedia.posts.exception.DAOException;
import com.socialmedia.posts.model.Post;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

@Service
public class PostsService {
    private static final Logger LOGGER = LoggerFactory.getLogger(PostsService.class);
    @Autowired
    private PostsDAO postsDAO;

    public void createPost(Post post) {
        LOGGER.info("Creating post");
        postsDAO.createPost(post);
        LOGGER.info("Post created successfully");
    }

    public void createRandomPost(int max_posts) {
        LOGGER.info("Saving {} posts to DB", max_posts);
        List<Post> posts = new ArrayList<>(max_posts);
        IntStream.rangeClosed(1, max_posts).parallel().forEach(i -> {
            Post post = new Post();
            post.setContent("Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi architecto beatae vitae dicta sunt explicabo. Nemo enim ipsam voluptatem quia voluptas sit aspernatur aut odit aut fugit, sed quia consequuntur magni dolores eos qui ratione voluptatem sequi nesciunt. Neque porro quisquam est, qui dolorem ipsum quia dolor sit amet, consectetur, adipisci velit, sed quia non numquam eius modi tempora incidunt ut labore et dolore magnam aliquam quaerat voluptatem. Ut enim ad minima veniam, quis nostrum exercitationem ullam corporis suscipit laboriosam, nisi ut aliquid ex ea commodi consequatur? Quis autem vel eum iure reprehenderit qui in ea voluptate velit esse quam nihil molestiae consequatur, vel illum qui dolorem eum fugiat quo voluptas nulla pariatur? At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi, id est laborum et dolorum fuga. Et harum quidem rerum facilis est et expedita distinctio. Nam libero tempore, cum soluta nobis est eligendi optio cumque nihil impedit quo minus id quod maxime placeat facere");
            posts.add(post);
        });
        postsDAO.createAllPost(posts);
        LOGGER.info("Added {} posts to DB", max_posts);
    }

    public void updatePost(Long postId, Post post) {
        LOGGER.info("Updating post");
        postsDAO.updatePost(postId, post);
        LOGGER.info("Post updated successfully");
    }
    public PostEntity getPost(Long postId) {
        Optional<PostEntity> postsOptional = postsDAO.getPost(postId);
        if(postsOptional.isPresent()){
            return postsOptional.get();
        }
        else
            throw new DAOException("Post not Found");
    }

    public Page<PostEntity> getPostByUserId(int pageNo, int pageSize) {
        Pageable pageable = PageRequest.of(pageNo, pageSize, Sort.by("createdDate").descending());
        return postsDAO.getPostByUserId(pageable);
    }

    public Page<PostEntity> getAllPosts(int pageNo, int pageSize){
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        return postsDAO.getAllPosts(pageable);
    }

    public void deletePost(Long postId) {
        LOGGER.info("Deleting post");
        postsDAO.deletePost(postId);
        LOGGER.info("Post deleted successfully");
    }
}