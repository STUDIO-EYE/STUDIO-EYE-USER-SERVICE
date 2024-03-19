package com.example.UserService.dto;

import lombok.Data;

@Data
public class UserDto {
    private String email;
    private String pwd;
    private String name;
    private String phoneNumber;
    private boolean isApproved;
    private String encryptedPwd;
}
