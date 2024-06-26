package com.example.UserService.service;

import com.example.UserService.domain.UserEntity;
import com.example.UserService.dto.EmailVerificationResult;
import com.example.UserService.dto.JWTAuthResponse;
import com.example.UserService.dto.UserResponse;
import com.example.UserService.vo.RequestLogin;
import com.example.UserService.vo.RequestUser;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface UserService{

    Optional<UserEntity> findOne(String email);

    JWTAuthResponse login(RequestLogin requestLogin);

    String register(RequestUser requestUser);

    UserResponse getUserResponseByUserId(Long userId);

    UserResponse findUserResponseByEmail(String email);

    JWTAuthResponse reissueAccessToken(String encryptedRefreshToken);

    void sendCodeToEmail(String toEmail);

    EmailVerificationResult verifiedCode(String email, String authCode);

    List<Long> getAllApprovedUserIds();

    ResponseEntity unregister(Long userId);

    List<UserResponse> getAllUsers();

    boolean updateApproved(Long userId, boolean approved);
}
