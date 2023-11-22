package com.project.musinsa.core.response;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
public class ResultResponse {
    private final LocalDateTime localDateTime = LocalDateTime.now();
    private final int status;
    private final String message;

    public ResultResponse(HttpStatus status) {
        this.status = status.value();
        this.message = status.name();
    }
}
