package com.userservice.dto.request;

import com.userservice.enums.UserStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ==========================================================
 * DTO Name : PatchUserRequest
 *
 * Purpose:
 * Used only for PATCH User API.
 *
 * PATCH means Partial Update.
 *
 * Therefore, all fields are optional.
 *
 * Example:
 *
 * {
 *     "firstName": "Jerry"
 * }
 *
 * In this request:
 *
 * firstName -> update
 * lastName  -> don't update
 * email     -> don't update
 * phone     -> don't update
 * status    -> don't update
 * ==========================================================
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatchUserRequest {

    /**
     * User First Name.
     *
     * Optional for PATCH.
     *
     * We intentionally do not use @NotBlank
     * because PATCH does not require every field.
     */
    private String firstName;

    /**
     * User Last Name.
     *
     * Optional for PATCH.
     */
    private String lastName;

    /**
     * User Email.
     *
     * Optional for PATCH.
     *
     * If email is provided,
     * it must have a valid email format.
     */
    @Email(message = "Invalid email format")
    private String email;

    /**
     * User Phone Number.
     *
     * Optional for PATCH.
     *
     * If phone is provided,
     * it must contain exactly 10 digits.
     */
    @Pattern(
            regexp = "^[0-9]{10}$",
            message = "Phone number must contain exactly 10 digits"
    )
    private String phone;

    /**
     * User Status.
     *
     * Optional for PATCH.
     *
     * Allowed values come from UserStatus enum.
     */
    private UserStatus status;
}