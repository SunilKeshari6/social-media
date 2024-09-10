package com.socialmedia.posts.dao;

import com.socialmedia.posts.entity.PostEntity;
import com.socialmedia.posts.exception.DAOException;
import com.socialmedia.posts.model.Post;
import com.socialmedia.posts.repository.PostsRepository;
import com.socialmedia.posts.service.CustomUserDetailsService;
import com.socialmedia.posts.util.AuditUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Component
public class PostsDAO {

    private static final Logger LOGGER = LoggerFactory.getLogger(PostsDAO.class);
    @Autowired
    private PostsRepository postsRepository;

    public void createPost(Post post) {
        PostEntity postEntity = mapToEntity(post);
        AuditUtil.addAuditDetails(postEntity);
        postsRepository.save(postEntity);
    }

    public void createAllPost(List<Post> posts) {
        List<PostEntity> postEntityList = new ArrayList<>(posts.size());
        posts.forEach(post -> {
            PostEntity postEntity = mapToEntity(post);
            AuditUtil.addAuditDetails(postEntity);
            postEntityList.add(postEntity);
        });
        postsRepository.saveAll(postEntityList);
    }

    public void updatePost(Long postId, Post post) {
        String username = CustomUserDetailsService.userPrincipal.getUsername();
        Optional<PostEntity> postsOptional = postsRepository.findByUsernameAndPostId(username, postId);
        if(postsOptional.isPresent()){
            PostEntity postEntity = postsOptional.get();
            postEntity.setContent(post.getContent());
            postEntity.setModifiedDate(new Date());
            postsRepository.save(postEntity);
        }
        else {
            LOGGER.error("Can't update. Post deleted/does not exist");
            throw new DAOException("Can't update. Post deleted/does not exist");
        }
    }

    public Optional<PostEntity> getPost(Long postId) {
        Optional<PostEntity> postsOptional = postsRepository.findById(postId);
        return postsOptional;
    }

    public Page<PostEntity> getPostByUserId(Pageable pageable) {
        return postsRepository.findAllByUsername(CustomUserDetailsService.userPrincipal.getUsername(), pageable);
    }

    public Page<PostEntity> getAllPosts(Pageable pageable){
        return postsRepository.findAll(pageable);
    }

    public void deletePost(Long postId) {
        postsRepository.deleteById(postId);
    }

    private PostEntity mapToEntity(Post post) {
        PostEntity postEntity = new PostEntity();
        postEntity.setUsername(CustomUserDetailsService.userPrincipal.getUsername());
        postEntity.setContent(post.getContent());
        return postEntity;
    }

}