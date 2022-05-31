package com.rb.alwaysontheroad.chatservice.shared.exception;

public class NoSecurityContextException extends RuntimeException {
    public NoSecurityContextException(String message) {
        super(message);
    }
}
