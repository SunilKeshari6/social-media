package com.socialmedia.posts.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
    private String errorCode;
    private String exception;
    private String date;
}
