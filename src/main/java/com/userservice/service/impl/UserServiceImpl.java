package com.userservice.service.impl;

import com.userservice.dto.request.PatchUserRequest;
import com.userservice.dto.request.UserRequest;
import com.userservice.dto.response.PageResponse;
import com.userservice.dto.response.UserResponse;
import com.userservice.exception.DuplicateEmailException;
import com.userservice.exception.UserNotFoundException;
import jakarta.transaction.Transactional;
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
import com.userservice.enums.UserStatus;

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
     * Update existing user.
     *
     * @param id User ID
     * @param request Updated user details
     * @return Updated user response
     */
    @Override
    public UserResponse updateUser(Long id, UserRequest request) {

        // Log update request.
        log.info("Updating user with id: {}", id);

        // Retrieve existing user.
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with id: {}", id);
                    return new UserNotFoundException(
                            "User not found with id: " + id
                    );
                });

        // Validate duplicate email only if email is changed.
        if (!existingUser.getEmail().equals(request.getEmail())
                && userRepository.existsByEmail(request.getEmail())) {

            log.error("Email already exists: {}", request.getEmail());

            throw new DuplicateEmailException(
                    "Email already exists: " + request.getEmail()
            );
        }

        // Update user details.
        existingUser.setFirstName(request.getFirstName());
        existingUser.setLastName(request.getLastName());
        existingUser.setEmail(request.getEmail());
        existingUser.setPhone(request.getPhone());
        existingUser.setStatus(request.getStatus());

        // Save updated user.
        User updatedUser = userRepository.save(existingUser);

        // Log successful update.
        log.info("User updated successfully with id: {}", id);

        // Convert entity to response DTO.
        return mapToUserResponse(updatedUser);
    }

    /**
     * ==========================================================
     * PATCH USER
     *
     * Performs a partial update of an existing user.
     *
     * PATCH rule:
     * Only the fields supplied by the client
     * will be updated.
     *
     * Fields that are null will remain unchanged.
     * ==========================================================
     */
    @Override
    public UserResponse patchUser(
            Long id,
            PatchUserRequest request) {

        /**
         * STEP 1:
         *
         * Log the incoming PATCH request.
         *
         * We log only the user ID.
         * We avoid logging sensitive user data.
         */
        log.info(
                "Partially updating user with id: {}",
                id
        );

        /**
         * STEP 2:
         *
         * Find the existing user.
         *
         * findById() returns Optional<User>.
         *
         * If user does not exist,
         * UserNotFoundException is thrown.
         */
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {

                    log.error(
                            "User not found with id: {}",
                            id
                    );

                    return new UserNotFoundException(
                            "User not found with id: " + id
                    );
                });

        /**
         * STEP 3:
         *
         * Update firstName only when
         * the client provides firstName.
         *
         * If firstName is null,
         * existing firstName remains unchanged.
         */
        if (request.getFirstName() != null) {

            existingUser.setFirstName(
                    request.getFirstName()
            );
        }

        /**
         * STEP 4:
         *
         * Update lastName only when
         * the client provides lastName.
         */
        if (request.getLastName() != null) {

            existingUser.setLastName(
                    request.getLastName()
            );
        }

        /**
         * STEP 5:
         *
         * Update email only when
         * the client provides email.
         */
        if (request.getEmail() != null) {

            /**
             * Check whether the requested email
             * is different from the current email.
             *
             * If it is different,
             * check whether another user
             * already owns that email.
             */
            if (!existingUser.getEmail()
                    .equals(request.getEmail())
                    &&
                    userRepository.existsByEmail(
                            request.getEmail())) {

                /**
                 * Log duplicate email attempt.
                 */
                log.error("Email already exists: {}",
                        request.getEmail());

                /**
                 * Use our existing project exception.
                 */
                throw new DuplicateEmailException(
                        "Email already exists: "
                                + request.getEmail());
            }

            /**
             * Email is available.
             * Update the existing user's email.
             */
            existingUser.setEmail(
                    request.getEmail());
        }

        /**
         * STEP 6:
         *
         * Update phone only when
         * the client provides phone.
         */
        if (request.getPhone() != null) {

            existingUser.setPhone(
                    request.getPhone());
        }

        /**
         * STEP 7:
         *
         * Update status only when
         * the client provides status.
         */
        if (request.getStatus() != null) {

            existingUser.setStatus(request.getStatus());
        }

        /**
         * STEP 8:
         *
         * Save the existing user.
         *
         * We are NOT creating a new User object.
         *
         * We modify the existing entity and
         * save it back to the database.
         */
        User updatedUser = userRepository.save(existingUser);

        /**
         * STEP 9:
         *
         * Log successful PATCH operation.
         */
        log.info(
                "User partially updated successfully with id: {}", id);

        /**
         * STEP 10:
         *
         * Convert updated Entity to
         * UserResponse DTO.
         *
         * We do not return Entity directly.
         */
        return mapToUserResponse(
                updatedUser
        );
    }

    @Override
    @Transactional
    public void deleteUser(Long id) {

        // Log incoming delete request.
        log.info("Delete user request received for ID : {}", id);

        // Retrieve user or throw exception if not found.
        User user = userRepository.findById(id)
                .orElseThrow(() -> {

                    // Log missing user before throwing exception.
                    log.warn("User not found with ID : {}", id);

                    return new UserNotFoundException(
                            "User not found with ID : " + id
                    );
                });

        // Delete the existing user.
        userRepository.delete(user);

        // Log successful deletion.
        log.info("User deleted successfully with ID : {}", id);
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
