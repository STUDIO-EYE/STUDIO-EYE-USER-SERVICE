package com.example.UserService.vo;

import lombok.Data;

import java.time.LocalDate;

@Data
public class ResponseUser {
    private String email;
    private String name;
    private String phoneNumber;
    private LocalDate createdAt;
    private boolean isApproved;
}
