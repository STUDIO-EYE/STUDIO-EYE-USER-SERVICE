package com.example.UserService.vo;

import lombok.Data;

@Data
public class ResponseUser {
    private String email;
    private String name;
    private String phoneNumber;
    private boolean isApproved;
}
