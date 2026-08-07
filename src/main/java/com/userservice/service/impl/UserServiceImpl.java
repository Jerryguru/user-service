package com.userservice.service.impl;

import com.userservice.dto.response.PageResponse;
import com.userservice.dto.response.UserResponse;
import com.userservice.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.userservice.dto.request.UserRegistrationRequest;
import com.userservice.dto.response.UserRegistrationResponse;
import com.userservice.entity.User;
import com.userservice.entity.UserAddress;
import com.userservice.enums.UserStatus;
import com.userservice.repository.UserAddressRepository;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import lombok.RequiredArgsConstructor;

/**
 * ==========================================================
 * Class Name : UserServiceImpl
 *
 * Description:
 * Implements all business operations related to User.
 *
 * Responsibilities:
 * 1. Validate User.
 * 2. Create User.
 * 3. Create Address.
 * 4. Save into Database.
 * 5. Return Response.
 * ==========================================================
 */

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    /**
     * Repository for User table.
     */
    private final UserRepository userRepository;

    /**
     * Repository for Address table.
     */
    private final UserAddressRepository userAddressRepository;

    @Override
    public UserRegistrationResponse registerUser(
            UserRegistrationRequest request) {

        /**
         * Check whether the Email already exists.
         */
        if (userRepository.existsByEmail(request.getEmail())) {

            throw new RuntimeException("Email already exists.");

        }

        /**
         * Create User Entity.
         */
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhoneNumber())
                .status(UserStatus.ACTIVE)
                .build();

        /**
         * Create User Address Entity.
         */
        UserAddress address = UserAddress.builder()
                .addressLine1(request.getStreetAddress())
                .addressLine2(request.getStreetAddress2())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .addressType(request.getAddressType())
                .isDefault(true)
                .build();

        /**
         * Establish relationship between User and Address.
         */
        address.setUser(user);

        user.getAddresses().add(address);

        /**
         * Save User into Database.
         */
        User savedUser = userRepository.save(user);
        /**
         * Build Registration Response.
         */
        return UserRegistrationResponse.builder()
                .id(savedUser.getId())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .message("User Registered Successfully")
                .build();
    }

    /**
     * ==========================================================
     * Retrieves User Details using User ID.
     *
     * Flow:
     * 1. Validate User ID.
     * 2. Fetch User from Database.
     * 3. Throw Exception if User not found.
     * 4. Convert Entity into Response DTO.
     * 5. Return Response.
     * ==========================================================
     *
     * @param id User ID
     * @return UserResponse
     */
    @Override
    public UserResponse getUserById(Long id) {

        /**
         * Log incoming request.
         */
        log.info("Fetching user details for ID : {}", id);

        /**
         * Fetch user from database.
         * Throw exception if user does not exist.
         */
        User user = userRepository.findById(id)
                .orElseThrow(() -> {

                    log.error("User not found with ID : {}", id);

                    return new UserNotFoundException(
                            "User not found with ID : " + id);
                });

        /**
         * User found successfully.
         */
        log.info("User found successfully with ID : {}", id);

        /**
         * Convert Entity into Response DTO.
         */
        log.info("Converting User Entity into UserResponse DTO.");

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setStatus(user.getStatus());
        response.setCreatedDate(user.getCreatedDate());
        response.setUpdatedDate(user.getUpdatedDate());

        /**
         * DTO prepared successfully.
         */
        log.info("UserResponse prepared successfully for ID : {}", id);

        /**
         * Return response.
         */
        return response;
    }

    /**
     * ==========================================================
     * Retrieves all users with Pagination and Sorting.
     *
     * Flow:
     * 1. Create Sort object.
     * 2. Create Pageable object.
     * 3. Fetch Users from Database.
     * 4. Convert Entity to DTO.
     * 5. Build PageResponse.
     * 6. Return Response.
     * ==========================================================
     *
     * @param page Current page number
     * @param size Number of records per page
     * @param sortBy Field name used for sorting
     * @param direction Sorting direction (ASC / DESC)
     * @return Paginated UserResponse
     */
    @Override
    public PageResponse<UserResponse> getAllUsers(
            int page,
            int size,
            String sortBy,
            String direction) {

        /**
         * Log incoming request.
         */
        log.info(
                "Fetching users with page={}, size={}, sortBy={}, direction={}",
                page,
                size,
                sortBy,
                direction
        );

        /**
         * Create Sort object.
         */
        Sort sort = Sort.by(
                Sort.Direction.fromString(direction),
                sortBy
        );
        /**
         * Sort object created.
         */
        log.info("Sorting users by '{}' in '{}' order.",
                sortBy,
                direction
        );

        /**
         * Create Pageable object.
         */
        Pageable pageable = PageRequest.of(
                page,
                size,
                sort
        );
        /**
         * Pageable object created.
         */
        log.info(
                "Pageable created successfully. Page={}, Size={}",
                page,
                size
        );

        /**
         * Fetch users from database.
         */
        log.info("Fetching users from database.");

        Page<User> userPage = userRepository.findAll(pageable);

        /**
         * Pageable object created.
         */
        log.info("Pageable created successfully. Page={}, Size={}",
                page,
                size
        );


        /**
         * Database returned records.
         */
        log.info(
                "Database returned {} records for current page.",
                userPage.getNumberOfElements()
        );

        /**
         * Check whether users are available.
         */
        if (userPage.isEmpty()) {

            log.info("No users found in database.");

        }

        log.info("Fetched {} users from database.",
                userPage.getNumberOfElements());

        /**
         * Convert Entity into DTO.
         */
        List<UserResponse> responseList =
                userPage.getContent()
                        .stream()
                        .map(this::mapToUserResponse)
                        .toList();

        log.info(
                "Successfully converted User entities into UserResponse DTOs.");

        /**
         * Prepare PageResponse.
         */
        PageResponse<UserResponse> response =
                PageResponse.<UserResponse>builder()
                        .content(responseList)
                        .page(userPage.getNumber())
                        .size(userPage.getSize())
                        .totalElements(userPage.getTotalElements())
                        .totalPages(userPage.getTotalPages())
                        .first(userPage.isFirst())
                        .last(userPage.isLast())
                        .build();
        /**
         * PageResponse prepared successfully.
         */
        log.info(
                "Returning paginated response with {} total elements and {} total pages.",
                userPage.getTotalElements(),
                userPage.getTotalPages());

        return response;
    }

    @Override
    public UserResponse getUserByEmail(String email) {

        /**
         * Log incoming request.
         */
        log.info("Fetching user with email: {}", email);

        /**
         * Fetch user from database.
         */
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> {
                    log.error("User not found with email: {}", email);
                    return new UserNotFoundException(
                            "User not found with email: " + email
                    );
                });

        /**
         * Log successful retrieval.
         */
        log.info("User found successfully with email: {}", email);

        /**
         * Convert Entity to DTO.
         */
        return mapToUserResponse(user);
    }


    /**
     * ==========================================================
     * Converts User Entity into UserResponse DTO.
     *
     * @param user User Entity
     * @return UserResponse
     * ==========================================================
     */
    private UserResponse mapToUserResponse(
            User user) {

        UserResponse response = new UserResponse();

        response.setId(user.getId());
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setEmail(user.getEmail());
        response.setPhone(user.getPhone());
        response.setStatus(user.getStatus());
        response.setCreatedDate(user.getCreatedDate());
        response.setUpdatedDate(user.getUpdatedDate());

        return response;
    }



}











































































































































































































































































































/*
package com.userservice.service.impl;

import com.userservice.enums.UserRole;
import com.userservice.enums.UserStatus;
import org.springframework.stereotype.Service;

import com.userservice.dto.request.UserRegistrationRequest;
import com.userservice.dto.response.UserRegistrationResponse;
import com.userservice.repository.UserAddressRepository;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;

import com.userservice.entity.User;
import com.userservice.entity.UserAddress;

import lombok.RequiredArgsConstructor;

*/
/**
 * Service implementation for User operations.
 *//*

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    // Repository for User table operations
    private final UserRepository userRepository;

    // Repository for UserAddress table operations
    private final UserAddressRepository userAddressRepository;

    */
/**
     * Registers a new user.
     *//*

    @Override
    public UserRegistrationResponse registerUser(UserRegistrationRequest request) {

        // Check username already exists
        if (userRepository.existsByUserName(request.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        // Check email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new RuntimeException("Email already exists");
        }

        // Create User Entity

        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .phone(request.getPhoneNumber())
                .status(UserStatus.ACTIVE)
                .build();
        */
/*User user = User.builder()
                .userName(request.getUserName())
                .email(request.getEmail())
                .password(request.getPassword())
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .userRole(UserRole.CUSTOMER)
                .active(false)
                .isLocked(false)
                .failedLoginAttempts(0)
                .build();*//*


        // Create Address Entity
        UserAddress address = UserAddress.builder()
                .addressLine1(request.getStreetAddress())
                .addressLine2(request.getStreetAddress2())
                .city(request.getCity())
                .state(request.getState())
                .country(request.getCountry())
                .postalCode(request.getPostalCode())
                .addressType(request.getAddressType())
                .isDefault(true)
                .build();

       */
/* UserAddress address = UserAddress.builder()
                .streetAddress(request.getStreetAddress())
                .streetAddress2(request.getStreetAddress2())
                .city(request.getCity())
                .state(request.getState())
                .postalCode(request.getPostalCode())
                .country(request.getCountry())
                .addressType(request.getAddressType())
                .isDefault(true)
                .build();*//*


        // Set Relationship
        address.setUser(user);
        user.getAddresses().add(address);

        // Save User
        User savedUser = userRepository.save(user);

        // Response next chapter

        // Return Response
        return UserRegistrationResponse.builder()
                .id(savedUser.getId())
               // .userName(savedUser.getUserName())
                .email(savedUser.getEmail())
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .message("User Registered Successfully")
                .build();
    }

}*/
