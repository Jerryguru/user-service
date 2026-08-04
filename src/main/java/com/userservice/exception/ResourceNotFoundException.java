package com.userservice.exception;

/**
 * ==========================================================
 * Exception Name : ResourceNotFoundException
 *
 * Description:
 * Thrown when requested resource is not found.
 * ==========================================================
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {

        super(message);

    }

}