package com.example.UserService.controller;

import com.example.UserService.config.JwtTokenProvider;
import com.example.UserService.dto.EmailVerificationResult;
import com.example.UserService.dto.JWTAuthResponse;
import com.example.UserService.dto.UserResponse;
import com.example.UserService.exception.BusinessLogicException;
import com.example.UserService.exception.ExceptionCode;
import com.example.UserService.service.UserService;
import com.example.UserService.vo.RequestLogin;
import com.example.UserService.vo.RequestUser;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user-service")
public class UserController {

    private final Environment env;
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/health_check")
    public String status(){
        return "It's Working in User Service";
    }

    @GetMapping("/welcome")
    public String welcome(){
        return env.getProperty("greeting.message");
    }

    @PostMapping("/login")
    public ResponseEntity<JWTAuthResponse> login(@RequestBody RequestLogin requestLogin){
        JWTAuthResponse token = userService.login(requestLogin);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RequestUser requestUser){
        String response = userService.register(requestUser);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/response_userById/{userId}")
    public ResponseEntity<UserResponse> findUserResponseByUserId(@PathVariable("userId") Long userId) {
        try {
            UserResponse userResponse = userService.getUserResponseByUserId(userId);
            return ResponseEntity.ok().body(userResponse);
        } catch (BusinessLogicException e) {
            ExceptionCode exceptionCode = e.getExceptionCode();
            int status = exceptionCode.getStatus();
            String message = exceptionCode.getMessage();
            return ResponseEntity.status(status).body(new UserResponse(message));
        }
    }

    @GetMapping("/response_userByEmail/{email}")
    public ResponseEntity<UserResponse> findUserResponseByEmail(@PathVariable String email) {
        try {
            UserResponse userResponse = userService.findUserResponseByEmail(email);
            return ResponseEntity.ok().body(userResponse);
        } catch (BusinessLogicException e) {
            ExceptionCode exceptionCode = e.getExceptionCode();
            int status = exceptionCode.getStatus();
            String message = exceptionCode.getMessage();
            return ResponseEntity.status(status).body(new UserResponse(message));
        }
    }

    //토큰 재발급
    @PatchMapping("/reissue")
    public ResponseEntity<JWTAuthResponse> reissue(HttpServletRequest request,
                                  HttpServletResponse response) {

        String refreshToken = jwtTokenProvider.resolveRefreshToken(request);
        JWTAuthResponse newAccessToken = userService.reissueAccessToken(refreshToken);
        return ResponseEntity.ok(newAccessToken);
    }

    //이메일 인증번호 전송
    @PostMapping("/emails/verification-requests")
    public ResponseEntity sendMessage(@RequestParam("email") @Valid String email) {

        userService.sendCodeToEmail(email);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //이메일 인증번호 검증
    @GetMapping("/emails/verifications")
    public ResponseEntity verificationEmail(@RequestParam("email") @Valid @Email String email,
                                            @RequestParam("code") String authCode) {

        EmailVerificationResult response = userService.verifiedCode(email, authCode);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}

