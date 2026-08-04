package com.userservice.exception;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

/**
 * Standard Error Response.
 */
@Getter
@Setter
@Builder
public class ErrorResponse {

    /**
     * Error Time
     */
    private LocalDateTime timestamp;

    /**
     * HTTP Status Code
     */
    private int status;

    /**
     * Error Message
     */
    private String message;

    /**
     * Request Path
     */
    private String path;

}