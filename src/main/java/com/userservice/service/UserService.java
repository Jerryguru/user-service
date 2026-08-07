package com.userservice.service;

import com.userservice.dto.request.UserRegistrationRequest;
import com.userservice.dto.response.UserRegistrationResponse;
import com.userservice.dto.response.UserResponse;

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
