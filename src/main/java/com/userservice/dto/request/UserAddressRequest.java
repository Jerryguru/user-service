package com.userservice.dto.request;

import com.userservice.enums.AddressType;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

/**
 * Request DTO used while adding or updating a user address.
 */
@Getter
@Setter
public class UserAddressRequest {

    @NotBlank
    private String addressLine1;

    private String addressLine2;

    @NotBlank
    private String city;

    @NotBlank
    private String state;

    @NotBlank
    private String country;

    @NotBlank
    private String postalCode;

    private AddressType addressType;

    private Boolean isDefault;

}