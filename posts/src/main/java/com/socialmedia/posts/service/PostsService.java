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
import org.springframework.stereotype.Service;
import java.util.Optional;

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