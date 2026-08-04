package com.userservice.service.impl;

import org.springframework.stereotype.Service;

import com.userservice.dto.request.UserRegistrationRequest;
import com.userservice.dto.response.UserRegistrationResponse;
import com.userservice.entity.User;
import com.userservice.entity.UserAddress;
import com.userservice.enums.UserStatus;
import com.userservice.repository.UserAddressRepository;
import com.userservice.repository.UserRepository;
import com.userservice.service.UserService;

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
