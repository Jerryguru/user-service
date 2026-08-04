package com.userservice.entity;

import java.time.LocalDateTime;

import com.userservice.enums.AddressType;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 * ==========================================================
 * Entity Name : UserAddress
 *
 * Description:
 * Represents Address information of a User.
 *
 * One User can have Multiple Addresses.
 * Multiple Addresses belong to One User.
 *
 * Database Table :
 * user_addresses
 * ==========================================================
 */

@Getter                     // Generates getters
@Setter                     // Generates setters
@NoArgsConstructor          // Empty Constructor
@AllArgsConstructor         // Constructor with all fields
@Builder                    // Builder Pattern
@Entity                     // Maps class to Database Table
@Table(name = "user_addresses")
public class UserAddress {

    /**
     * Primary Key
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Address Line 1
     */
    @Column(name = "address_line1", nullable = false, length = 100)
    private String addressLine1;

    /**
     * Address Line 2
     */
    @Column(name = "address_line2", length = 100)
    private String addressLine2;

    /**
     * City
     */
    @Column(nullable = false, length = 50)
    private String city;

    /**
     * State
     */
    @Column(nullable = false, length = 50)
    private String state;

    /**
     * Country
     */
    @Column(nullable = false, length = 50)
    private String country;

    /**
     * Postal Code
     */
    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    /**
     * Default Address
     */
    @Column(name = "is_default")
    private Boolean isDefault;

    /**
     * Address Type
     * HOME
     * OFFICE
     * PERMANENT
     * OTHER
     */
    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false)
    private AddressType addressType;

    /**
     * Many Addresses belong to One User.
     *
     * user_id -> Foreign Key
     */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    /**
     * Record Created Time
     */
    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
    private LocalDateTime createdDate;

    /**
     * Record Updated Time
     */
    @UpdateTimestamp
    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

}











































































































































































































































/*
package com.userservice.entity;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.userservice.enums.AddressType;
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
@Table(name = "user_addresses")
public class UserAddress {

    */
/**
     * Primary Key
     *//*

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    */
/**
     * Address Line 1
     * Example: House No, Street Name
     *//*

    @Column(name = "address_line1", nullable = false, length = 100)
    private String addressLine1;

    */
/**
     * Address Line 2
     * Example: Area, Landmark (Optional)
     *//*

    @Column(name = "address_line2", length = 100)
    private String addressLine2;

    */
/**
     * City Name
     *//*

    @Column(nullable = false, length = 50)
    private String city;

    */
/**
     * State Name
     *//*

    @Column(nullable = false, length = 50)
    private String state;

    */
/**
     * Country Name
     *//*

    @Column(nullable = false, length = 50)
    private String country;

    */
/**
     * Postal / ZIP Code
     *//*

    @Column(name = "postal_code", nullable = false, length = 10)
    private String postalCode;

    */
/**
     * Indicates whether this is the user's default address.
     * true  = Default Address
     * false = Non-Default Address
     *//*

    @Column(name = "is_default")
    private Boolean isDefault;

    */
/**
     * Record Creation Time
     *//*

    @CreationTimestamp
    @Column(name = "created_date", updatable = false)
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
     * Many addresses belong to one user.
     * JsonBackReference prevents infinite JSON recursion.
     *//*

 */
/*   @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    @JsonBackReference
    private User user;*//*

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;



    */
/**
     * Type of address.
     * Example: HOME, OFFICE, PERMANENT, OTHER
     *//*

    @Enumerated(EnumType.STRING)
    @Column(name = "address_type", nullable = false, length = 20)
    private AddressType addressType;
}*/
