package com.example.UserService.exception;

import com.example.UserService.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BusinessLogicException.class)
    public ResponseEntity<ApiResponse<?>> handleBusinessLogicException(BusinessLogicException e) {
        ApiResponse<?> response = ApiResponse.withError(e.getExceptionCode());
        return new ResponseEntity<>(response, e.getExceptionCode().getStatus());
    }
}
