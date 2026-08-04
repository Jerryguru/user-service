package com.userservice.dto.response;

import java.time.LocalDateTime;

import com.userservice.enums.AddressType;

import lombok.Getter;
import lombok.Setter;

/**
 * Response DTO returned for user address details.
 */
@Getter
@Setter
public class UserAddressResponse {

    private Long id;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String state;

    private String country;

    private String postalCode;

    private AddressType addressType;

    private Boolean isDefault;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

}