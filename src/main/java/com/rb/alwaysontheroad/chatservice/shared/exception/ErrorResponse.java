package com.rb.alwaysontheroad.chatservice.shared.exception;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
public class ErrorResponse {
    private String message;
    private LocalDateTime time;
}
