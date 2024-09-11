package com.socialmedia.posts.model;


import jakarta.validation.constraints.Size;
import lombok.Data;


@Data
public class Post {
    @Size(min = 1, max = 1000, message = "Text must be within 1000 characters")
    private String content;
}