package com.userservice.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServletRequest;

/**
 * ==========================================================
 * Global Exception Handler
 *
 * Handles all application exceptions.
 * ==========================================================
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Handle Duplicate Email Exception.
     */
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ErrorResponse> handleDuplicateEmail(

            DuplicateEmailException ex,

            HttpServletRequest request){

        ErrorResponse response = ErrorResponse.builder()

                .timestamp(LocalDateTime.now())

                .status(HttpStatus.CONFLICT.value())

                .message(ex.getMessage())

                .path(request.getRequestURI())

                .build();

        return ResponseEntity

                .status(HttpStatus.CONFLICT)

                .body(response);

    }

}