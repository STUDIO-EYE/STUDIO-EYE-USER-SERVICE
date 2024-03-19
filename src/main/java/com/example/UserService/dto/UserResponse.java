package com.example.UserService.dto;

import lombok.Getter;

//feignClient 응답용

@Getter
public class UserResponse {
    private Long id;
    private String email;
    private String name;
    private String phoneNumber;
    private boolean isApproved;
    private String message;

    public UserResponse(Long id, String email, String name, String phoneNumber, boolean isApproved) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.isApproved = isApproved;
    }

    public UserResponse(String message) {
        this.message = message;
    }
}
