package com.example.UserService.dto;

import com.example.UserService.exception.ExceptionCode;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ApiResponse<T> {

	// API 상태 코드
	private int code;

	// API 상태
	private HttpStatus status;

	// API 응답 메시지
	private String message;

	// API 응답 데이터
	private T data;

	private ApiResponse(HttpStatus status, String message, T data) {
		this.code = status.value();
		this.status = status;
		this.message = message;
		this.data = data;
	}

	public static <T> ApiResponse<T> of(HttpStatus status, String message, T data) {
		return new ApiResponse<>(status, message, data);
	}

	public static <T> ApiResponse<T> of(HttpStatus status, T data) {
		return of(status, status.name(), data);
	}

	public static <T> ApiResponse<T> ok(T data) {
		return of(HttpStatus.OK, data);
	}

	public static <T> ApiResponse<T> ok(String message) {
		return of(HttpStatus.OK, message, null);
	}

	public static <T> ApiResponse<T> ok(String message, T data) {
		return of(HttpStatus.OK, message, data);
	}

	public static <T> ApiResponse<T> withError(ExceptionCode exceptionCode, T data) {
		return new ApiResponse<>(exceptionCode.getStatus(), exceptionCode.getMessage(), data);
	}

	public static <T> ApiResponse<T> withError(ExceptionCode exceptionCode) {
		return withError(exceptionCode, null);
	}
}