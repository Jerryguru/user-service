package com.userservice.service;

import com.userservice.dto.request.PatchUserRequest;
import com.userservice.dto.request.UserRegistrationRequest;
import com.userservice.dto.request.UserRequest;
import com.userservice.dto.response.PageResponse;
import com.userservice.dto.response.UserRegistrationResponse;
import com.userservice.dto.response.UserResponse;

import java.util.List;

/**
 * ==========================================================
 * Interface Name : UserService
 *
 * Description:
 * Defines all business operations related to User.
 *
 * Implementation Class:
 * UserServiceImpl
 * ==========================================================
 */
public interface UserService {

    /**
     * Registers a new User.
     *
     * @param request User Registration Request
     * @return User Registration Response
     */
    UserRegistrationResponse registerUser(
            UserRegistrationRequest request);

    /**
     * Retrieves user details using the user ID.
     *
     * @param id User primary key
     * @return UserResponse containing user details
     */
    UserResponse getUserById(Long id);


    /**
     * Retrieves all users.
     *
     * @return List of UserResponse
     */
  //  List<UserResponse> getAllUsers();
    /**
     * Retrieves all users with Pagination and Sorting.
     *
     * Flow:
     * 1. Accept page details.
     * 2. Accept sorting details.
     * 3. Fetch users from database.
     * 4. Convert Entity into DTO.
     * 5. Return PageResponse.
     *
     * @param page Page Number
     * @param size Page Size
     * @param sortBy Sort Field
     * @param direction Sort Direction
     * @return PageResponse<UserResponse>
     */
    PageResponse<UserResponse> getAllUsers(
            int page,
            int size,
            String sortBy,
            String direction);

    /**
     * Fetch user by email.
     *
     * @param email user email
     * @return UserResponse
     */
    UserResponse getUserByEmail(String email);

    /**
     * Update existing user.
     *
     * @param id      User ID
     * @param request Updated user details
     * @return Updated UserResponse
     */
    UserResponse updateUser(
            Long id,
            UserRequest request
    );

    /**
     * Performs a partial update of an existing user.
     *
     * PATCH API uses this method.
     *
     * Only the fields supplied by the client
     * will be updated.
     *
     * @param id user ID
     * @param request partial update request
     * @return partially updated user response
     */
    UserResponse patchUser(
            Long id,
            PatchUserRequest request
    );
}
































































































































































































































/*
package com.userservice.service;

import com.userservice.dto.request.UserRegistrationRequest;
import com.userservice.dto.response.UserRegistrationResponse;

*/
/**
 * Service interface for User operations.
 *//*

public interface UserService {

    */
/**
     * Registers a new user into the system.
     *
     * @param request Registration request received from client
     * @return Registered user details
     *//*

    UserRegistrationResponse registerUser(UserRegistrationRequest request);

}*/
