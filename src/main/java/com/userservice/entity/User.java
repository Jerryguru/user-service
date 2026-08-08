
package com.userservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.userservice.enums.UserStatus;

import jakarta.persistence.*;
import lombok.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * ==========================================================
 * Entity Name : User
 *
 * Description:
 * Represents a User in the system.
 * Each User can have multiple addresses.
 *
 * Database Table :
 * users
 * ==========================================================
 */

@Getter                     // Generates getters
@Setter                     // Generates setters
@NoArgsConstructor          // Empty constructor
@AllArgsConstructor         // Constructor with all fields
@Builder                    // Builder Pattern
@Entity                     // Maps this class to Database Table
@Table(name = "users")      // Database table name
public class User {

    /**
     * Primary Key
     * Auto Increment
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * User First Name
     */
    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    /**
     * User Last Name
     */
    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    /**
     * User Email
     * Must be Unique
     */
    @Column(nullable = false, unique = true, length = 100)
    private String email;

    /**
     * User Phone Number
     * Must be Unique
     */
    @Column(nullable = false, unique = true, length = 10)
    private String phone;

    /**
     * User Status
     * ACTIVE
     * INACTIVE
     * BLOCKED
     */
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status;

    /**
     * Record Creation Time
     * Automatically inserted by Hibernate
     */
    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    /**
     * Record Last Updated Time
     * Automatically updated by Hibernate
     */
    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    /**
     * One User can have Multiple Addresses.
     *
     * mappedBy        -> Parent Side
     * cascade = ALL   -> Save/Delete User also affects Addresses
     * orphanRemoval   -> Removes orphan addresses automatically
     */
    @Builder.Default
    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private List<UserAddress> addresses = new ArrayList<>();

}






















































































































































































































































































































/*
package com.userservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.userservice.enums.UserStatus;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "users")
public class User {

    */
/**
     * Primary Key
     *//*

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    */
/**
     * User First Name
     *//*

    @Column(name = "first_name", nullable = false, length = 50)
    private String firstName;

    */
/**
     * User Last Name
     *//*

    @Column(name = "last_name", nullable = false, length = 50)
    private String lastName;

    */
/**
     * User Email (Unique)
     *//*

    @Column(nullable = false, unique = true, length = 100)
    private String email;

    */
/**
     * User Phone Number (Unique)
     *//*

    @Column(nullable = false, unique = true, length = 10)
    private String phone;

    */
/**
     * User Status
     *//*

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private UserStatus status;

    */
/**
     * Record Creation Time
     *//*

    @CreationTimestamp
    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate;

    */
/**
     * Record Last Updated Time
     *//*

    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    */
/**
     * One user can have multiple addresses.
     *//*

    @Builder.Default
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserAddress> addresses = new ArrayList<>();
}*/
