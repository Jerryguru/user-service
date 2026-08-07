package com.userservice.dto.response;

import java.time.LocalDateTime;

import com.userservice.enums.UserStatus;

import lombok.*;

/**
 * Response DTO returned to the client.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserResponse {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String phone;

    private UserStatus status;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}