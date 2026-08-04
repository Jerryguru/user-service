

package com.userservice.dto.request;

import com.userservice.enums.AddressType;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

/**
 * ==========================================================
 * DTO Name : UserRegistrationRequest
 *
 * Description:
 * Receives User Registration data from Client.
 * ==========================================================
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegistrationRequest {

    /**
     * User First Name
     */
    @NotBlank(message = "First Name is required")
    private String firstName;

    /**
     * User Last Name
     */
    @NotBlank(message = "Last Name is required")
    private String lastName;

    /**
     * Email Address
     */
    @Email(message = "Invalid Email")
    @NotBlank(message = "Email is required")
    private String email;

    /**
     * Phone Number
     */
    @Size(min = 10, max = 10, message = "Phone Number must contain 10 digits")
    private String phoneNumber;

    /**
     * Address Line 1
     */
    @NotBlank(message = "Street Address is required")
    private String streetAddress;

    /**
     * Address Line 2
     */
    private String streetAddress2;

    /**
     * City
     */
    @NotBlank(message = "City is required")
    private String city;

    /**
     * State
     */
    @NotBlank(message = "State is required")
    private String state;

    /**
     * Country
     */
    @NotBlank(message = "Country is required")
    private String country;

    /**
     * Postal Code
     */
    @NotBlank(message = "Postal Code is required")
    private String postalCode;

    /**
     * Address Type
     */
    private AddressType addressType;

}























































































































































































































































































































/*
package com.userservice.dto.request;

import com.userservice.enums.AddressType;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

*/
/**
 * DTO used to register a new user.
 *//*

@Getter
@Setter
public class UserRegistrationRequest {

    // ---------------- User Details ----------------

    @NotBlank(message = "Username is required")
    @Size(min = 3, max = 50)
    private String userName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Password is required")
    @Size(min = 8, max = 100)
    private String password;

    @NotBlank(message = "First name is required")
    private String firstName;

    @NotBlank(message = "Last name is required")
    private String lastName;

    @Pattern(regexp = "^[0-9]{10}$",
            message = "Phone number must contain 10 digits")
    private String phoneNumber;

    // ---------------- Address Details ----------------

    @NotBlank(message = "Street address is required")
    private String streetAddress;

    private String streetAddress2;

    @NotBlank(message = "City is required")
    private String city;

    @NotBlank(message = "State is required")
    private String state;

    @NotBlank(message = "Postal code is required")
    private String postalCode;

    @NotBlank(message = "Country is required")
    private String country;

    private AddressType addressType;

}*/
