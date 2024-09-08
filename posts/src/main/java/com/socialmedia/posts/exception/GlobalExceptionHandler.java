package com.socialmedia.posts.exception;

import com.socialmedia.posts.model.ErrorCodes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.text.SimpleDateFormat;
import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({DAOException.class})
    public final ResponseEntity<ExceptionResponse> handleException(DAOException exception) {
        Date dateObj = new Date();
        String utcDate =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(dateObj);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.NOT_FOUND.code, exception.getMessage(), utcDate);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ServiceException.class})
    public final ResponseEntity<ExceptionResponse> handleException(ServiceException exception) {
        Date dateObj = new Date();
        String utcDate =  new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss").format(dateObj);
        ExceptionResponse exceptionResponse = new ExceptionResponse(ErrorCodes.USER_ALREAY_REGISTERED.code, exception.getMessage(), utcDate);
        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }
}