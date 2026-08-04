package com.userservice.exception;

/**
 * ==========================================================
 * Exception Name : DuplicateEmailException
 *
 * Description:
 * Thrown when email already exists.
 * ==========================================================
 */
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {

        super(message);

    }

}