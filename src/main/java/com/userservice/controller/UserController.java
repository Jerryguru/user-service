package com.userservice.controller;

import com.userservice.dto.response.UserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.userservice.dto.request.UserRegistrationRequest;
import com.userservice.dto.response.UserRegistrationResponse;
import com.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

/**
 * ==========================================================
 * Controller Name : UserController
 *
 * Description:
 * Exposes REST APIs related to User operations.
 *
 * Responsibilities:
 * 1. Receive Client Requests.
 * 2. Validate Request.
 * 3. Call Service Layer.
 * 4. Return Response.
 * ==========================================================
 */

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
@Slf4j
public class UserController {

    /**
     * Service Layer Dependency.
     */
    private final UserService userService;

    /**
     * Register New User.
     *
     * URL :
     * POST /api/v1/users/register
     */
    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        /**
         * Call Service Layer.
         */
        UserRegistrationResponse response =
                userService.registerUser(request);

        /**
         * Return HTTP 201 Created.
         */
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    /**
     * ==========================================================
     * API Name : Get User By ID
     *
     * Description:
     * Retrieves user details using the given User ID.
     *
     * URL:
     * GET /api/v1/users/{id}
     * ==========================================================
     *
     * @param id User ID
     * @return UserResponse
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable Long id) {

        /**
         * Log incoming request.
         */
        log.info("Received request to fetch user with ID : {}", id);

        /**
         * Call Service Layer.
         */
        UserResponse response = userService.getUserById(id);

        /**
         * Log successful response.
         */
        log.info("Successfully fetched user with ID : {}", id);

        /**
         * Return HTTP 200 OK response.
         */
        return ResponseEntity.ok(response);
    }
}



















































































































































































/*
package com.userservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import com.userservice.dto.request.UserRegistrationRequest;
import com.userservice.dto.response.UserRegistrationResponse;
import com.userservice.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

*/
/**
 * REST Controller for User APIs.
 *//*

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Validated
public class UserController {

    // Service Layer
    private final UserService userService;

    */
/**
     * Register a new user.
     *
     * @param request Registration Request
     * @return Registered User Details
     *//*

    @PostMapping("/register")
    public ResponseEntity<UserRegistrationResponse> registerUser(
            @Valid @RequestBody UserRegistrationRequest request) {

        UserRegistrationResponse response =
                userService.registerUser(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(response);
    }

}*/
