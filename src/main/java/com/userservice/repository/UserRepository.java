
package com.userservice.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.entity.User;

/**
 * ==========================================================
 * Repository Name : UserRepository
 *
 * Description:
 * Handles all database operations related to User Entity.
 *
 * JpaRepository already provides:
 * - save()
 * - findById()
 * - findAll()
 * - deleteById()
 * - existsById()
 * ==========================================================
 */

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    /**
     * Find User by Email.
     *
     * Used during:
     * - Login
     * - Fetch User by Email
     */
    Optional<User> findByEmail(String email);

    /**
     * Check whether Email already exists.
     *
     * Used during Registration.
     */
    boolean existsByEmail(String email);

}




































































































































/*
package com.userservice.repository;

import com.userservice.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    */
/**
     * Find user by email.
     *//*

    Optional<User> findByEmail(String email);

    */
/**
     * Check whether email already exists.
     *//*

    boolean existsByEmail(String email);

    */
/**
     * Find user by username.
     *//*

   // Optional<User> findByUserName(String userName);


    */
/**
     * Check whether username already exists.
     *//*

   // boolean existsByUserName(String userName);
}*/
