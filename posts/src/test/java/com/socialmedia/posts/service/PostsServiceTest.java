package com.socialmedia.posts.service;

import com.socialmedia.posts.dao.PostsDAO;
import com.socialmedia.posts.entity.PostEntity;
import com.socialmedia.posts.exception.DAOException;
import com.socialmedia.posts.model.Post;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
public class PostsServiceTest {

    @Mock
    private PostsDAO postsDAO;

    @InjectMocks
    private PostsService postsService;

    @Test
    public void testCreatePost() {
        Post post = new Post();
        doNothing().when(postsDAO).createPost(post);

        postsService.createPost(post);

        verify(postsDAO, times(1)).createPost(post);
    }

    @Test
    public void testUpdatePost() {
        Long postId = 1L;
        Post post = new Post();
        doNothing().when(postsDAO).updatePost(postId, post);

        postsService.updatePost(postId, post);

        verify(postsDAO, times(1)).updatePost(postId, post);
    }

    @Test
    public void testGetPost_Success() {
        Long postId = 1L;
        PostEntity postEntity = new PostEntity();
        when(postsDAO.getPost(postId)).thenReturn(Optional.of(postEntity));

        PostEntity result = postsService.getPost(postId);

        verify(postsDAO, times(1)).getPost(postId);
        assertSame(postEntity, result);
    }

    @Test
    public void testGetPost_NotFound() {
        Long postId = 1L;
        when(postsDAO.getPost(postId)).thenReturn(Optional.empty());

        try {
            postsService.getPost(postId);
        } catch (DAOException e) {
            assertEquals("Post not Found", e.getMessage());
        }

        verify(postsDAO, times(1)).getPost(postId);
    }

    @Test
    void testGetPostByUserId() {
        int pageNo = 0;
        int pageSize = 10;
        Page<PostEntity> postEntities = new PageImpl<>(Collections.singletonList(new PostEntity()));
        when(postsDAO.getPostByUserId(any())).thenReturn(postEntities);
        Page<PostEntity> result = postsService.getPostByUserId(pageNo, pageSize);
        assertNotNull(result);
        assertEquals(postEntities, result);
    }
    @Test
    public void testGetAllPosts() {
        Pageable pageable = PageRequest.of(0, 10);
        PostEntity postEntity = new PostEntity();
        Page<PostEntity> page = new PageImpl<>(List.of(postEntity));
        when(postsDAO.getAllPosts(pageable)).thenReturn(page);

        Page<PostEntity> result = postsService.getAllPosts(0, 10);

        verify(postsDAO, times(1)).getAllPosts(pageable);
        assertEquals(page, result);
    }

    @Test
    public void testDeletePost() {
        Long postId = 1L;
        doNothing().when(postsDAO).deletePost(postId);
        postsService.deletePost(postId);
        verify(postsDAO, times(1)).deletePost(postId);
    }
}
