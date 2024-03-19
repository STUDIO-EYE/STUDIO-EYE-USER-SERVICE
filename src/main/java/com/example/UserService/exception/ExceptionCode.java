package com.example.UserService.exception;

import lombok.Getter;

public enum ExceptionCode {
    MEMBER_NOT_FOUND(404, "회원을 찾을 수 없습니다"),
    RESOURCE_NOT_FOUND(404, "리소스를 찾을 수 없습니다"),
    BAD_REQUEST(400, "잘못된 요청입니다"),
    UNAUTHORIZED(401, "인증되지 않았습니다"),
    FORBIDDEN(403, "접근이 금지되었습니다"),
    INVALID_INPUT(422, "유효하지 않은 입력입니다"),
    INTERNAL_SERVER_ERROR(500, "내부 서버 오류입니다"),
    MEMBER_EXISTS(409, "이미 존재하는 회원입니다"),
    NO_SUCH_ALGORITHM(500, "존재하지 않는 알고리즘입니다"),
    UNABLE_TO_SEND_EMAIL(500, "이메일을 전송할 수 없습니다"),
    TOKEN_IS_NOT_SAME(401, "액세스 토큰과 리프레시 토큰이 일치하지 않습니다"),
    HEADER_REFRESH_TOKEN_NOT_EXISTS(400, "요청 헤더에 리프레시 토큰이 누락되었습니다");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int status, String message) {
        this.status = status;
        this.message = message;
    }
}
