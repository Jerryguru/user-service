package com.userservice.exception;

import java.time.LocalDateTime;

import lombok.*;

/**
 * Standard Error Response.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorResponse {

    /**
     * Error occurred time.
     */
    private LocalDateTime timestamp;

    /**
     * HTTP Status Code.
     */
    private int status;

    /**
     * HTTP Status Name.
     * Example:
     * NOT_FOUND
     * CONFLICT
     * BAD_REQUEST
     */
    private String error;

    /**
     * Detailed Error Message.
     */
    private String message;

    /**
     * API Request Path.
     */
    private String path;

}