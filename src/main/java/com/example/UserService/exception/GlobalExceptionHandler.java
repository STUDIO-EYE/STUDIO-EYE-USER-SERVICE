package com.example.UserService.exception;

import com.example.UserService.dto.ApiResponse;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ApiResponse<?> handleBusinessLogicException(BusinessLogicException e) {
        return ApiResponse.withError(e.getExceptionCode());
    }
}
