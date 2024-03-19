package com.example.UserService.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class JWTAuthResponse {
    private String tokenType;
    private String accessToken;
    private String refreshToken;
    private Long accessTokenExpireDate;
}

