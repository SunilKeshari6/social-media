package com.socialmedia.posts.integrationtest;

import com.socialmedia.posts.controller.PostsController;
import com.socialmedia.posts.entity.PostEntity;
import com.socialmedia.posts.model.Post;
import com.socialmedia.posts.service.PostsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import java.util.Collections;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class PostsControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private PostsService postsService;

    @InjectMocks
    private PostsController postsController;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(postsController).build();
    }

    @Test
    public void testGetPost() throws Exception {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostId(1L);
        when(postsService.getPost(anyLong())).thenReturn(postEntity);

        mockMvc.perform(get("/socialmedia/post/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.postId").value(1));
    }

    @Test
    public void testGetAllPosts() throws Exception {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostId(1L);
        Page<PostEntity> page = new PageImpl<>(Collections.singletonList(postEntity), PageRequest.of(0, 10), 1);
        when(postsService.getAllPosts(anyInt(), anyInt())).thenReturn(page);

        mockMvc.perform(get("/socialmedia/post?pageNo=0&pageSize=10"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.content[0].postId").value(1));
    }

    @Test
    public void testCreatePost() throws Exception {
        Post post = new Post();
        post.setUsername("User-1");
        post.setContent("Test Content");

        mockMvc.perform(post("/socialmedia/post")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"User-1\",\"content\":\"Test Content\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Post created successfully"));
    }

    @Test
    public void testUpdatePost() throws Exception {
        Post post = new Post();
        post.setUsername("User-1");
        post.setContent("Updated Content");

        mockMvc.perform(put("/socialmedia/post/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"username\":\"User-1\",\"content\":\"Updated Content\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Post updated successfully"));
    }

    @Test
    public void testDeletePost() throws Exception {
        mockMvc.perform(delete("/socialmedia/post/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Post deleted successfully"));
    }
}
