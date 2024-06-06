package com.example.UserService.exception;

import com.example.UserService.dto.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BlogAPIException.class)
    public ApiResponse<?> handleBlogAPIException(BlogAPIException e) {
        return ApiResponse.withError(ExceptionCode.MEMBER_EXISTS);
    }
}
