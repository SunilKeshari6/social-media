package com.socialmedia.posts.controller;

import com.socialmedia.posts.entity.PostEntity;
import com.socialmedia.posts.model.Post;
import com.socialmedia.posts.service.CustomUserDetailsService;
import com.socialmedia.posts.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/socialmedia")
public class PostsController {
    @Autowired
    PostsService postsService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @GetMapping("/post/{id}")
    public ResponseEntity<PostEntity> getPost(@PathVariable("id") Long postId) {
        return new ResponseEntity<>(postsService.getPost(postId), HttpStatus.OK);
    }

    @GetMapping("/post/userId")
    public ResponseEntity<Page<PostEntity>> getAllPostByUserId(@RequestParam(defaultValue = "0") int pageNo,
                                                            @RequestParam(defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(postsService.getPostByUserId(pageNo, pageSize), HttpStatus.OK);
    }

    @GetMapping("/post/all")
    public ResponseEntity<Page<PostEntity>> getAllPosts(@RequestParam(defaultValue = "0") int pageNo,
                                                       @RequestParam(defaultValue = "10") int pageSize) {
        return new ResponseEntity<>(postsService.getAllPosts(pageNo, pageSize), HttpStatus.OK);
    }

    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody Post post) {
        postsService.createPost(post);
        return new ResponseEntity<>("Post created successfully", HttpStatus.OK);
    }

    @PostMapping("/post/random")
    public ResponseEntity<String> createPost(@RequestParam(defaultValue = "100") int n) {
        postsService.createRandomPost(n);
        return new ResponseEntity<>("Post created successfully", HttpStatus.OK);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<String> updatePost(@PathVariable("id") Long postId, @RequestBody Post post) {
        postsService.updatePost(postId, post);
        return new ResponseEntity<>("Post updated successfully", HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable("id") Long postId){
        postsService.deletePost(postId);
        return new ResponseEntity<>("Post deleted successfully", HttpStatus.OK);
    }
}