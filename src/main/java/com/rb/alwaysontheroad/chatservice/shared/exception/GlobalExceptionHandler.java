package com.rb.alwaysontheroad.chatservice.shared.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    /* Catch WebClient interaction exception */
    @ExceptionHandler(WebClientResponseException.class)
    public ResponseEntity<ErrorResponse> handleWebClientResponseException(final WebClientResponseException ex) {
        log.error(
                "WebClientResponseException has occurred: Status: '{}', Body: '{}'",
                ex.getRawStatusCode(), ex.getResponseBodyAsString(), ex
        );
        return buildResponse(ex.getResponseBodyAsString(), HttpStatus.BAD_REQUEST);
    }

    /* Catch bean validation exception */
    @ExceptionHandler(WebExchangeBindException.class)
    public ResponseEntity<ErrorResponse> handleBeenValidationException(WebExchangeBindException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors()
                .forEach(error -> {
                    String fieldName = ((FieldError) error).getField();
                    String errorMessage = error.getDefaultMessage();
                    errors.put(fieldName, errorMessage);
                });
        return buildResponse(errors.toString(), HttpStatus.BAD_REQUEST);
    }

    /* Catch others */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleOthersException(Exception ex) {
        log.error("Exception has occurred: ", ex);
        return buildResponse(ex.toString(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    private ResponseEntity<ErrorResponse> buildResponse(String body, HttpStatus status) {
        return ResponseEntity.status(status)
                .contentType(MediaType.APPLICATION_JSON)
                .body(ErrorResponse.builder()
                        .message(body)
                        .time(LocalDateTime.now()).build()
                );
    }
}
