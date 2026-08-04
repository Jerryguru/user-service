package com.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.entity.UserAddress;

/**
 * ==========================================================
 * Repository Name : UserAddressRepository
 *
 * Description:
 * Handles database operations related to User Address.
 * ==========================================================
 */

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

}




































































































/*
package com.userservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userservice.entity.User;
import com.userservice.entity.UserAddress;

*/
/**
 * Repository for UserAddress entity.
 *//*

@Repository
public interface UserAddressRepository extends JpaRepository<UserAddress, Long> {

    */
/**
     * Get all addresses of a user.
     *//*

    List<UserAddress> findByUser(User user);

}*/
