package com.project.musinsa.core.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /**
     * 400 BAD_REQUEST : 잘못된 요청
     */
    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다."),
    /**
     * 404 NOT_FOUND: 리소스를 찾을 수 없음
     */
    POSTS_NOT_FOUND(HttpStatus.NOT_FOUND, "정보를 찾을 수 없습니다."),
    /**
     * 405 METHOD_NOT_ALLOWED : 허용되지 않은 Request Method 호출
     */
    METHOD_NOT_ALLOWED(HttpStatus.METHOD_NOT_ALLOWED, "허용되지 않은 요청입니다."),
    /**
     * 500 INTERNAL_SERVER_ERROR : 내부 서버 오류
     */
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 처리 중 오류가 발생했습니다."),

    /**
     * Null point 처리
     */
    REQUEST_DATA_NULL_POINT(HttpStatus.INTERNAL_SERVER_ERROR, "데이터가 적합하지 않습니다.");

    private final HttpStatus status;
    private final String message;
}
