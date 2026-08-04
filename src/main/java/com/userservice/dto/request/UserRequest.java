package com.userservice.dto.request;

import com.userservice.enums.UserStatus;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO used while creating or updating a user.
 */
@Getter
@Setter
public class UserRequest {

    @NotBlank(message = "First name is required")
    @Size(min = 3, max = 50)
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(min = 3, max = 50)
    private String lastName;

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    private String email;

    @NotBlank(message = "Phone number is required")
    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must contain 10 digits")
    private String phone;

    private UserStatus status;

}