package com.transactionorder.transactionorderservice.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
public class CustomerProfileDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private LocalDate dateOfBirth;
    private String gender;
    private Boolean isActive;
    private String accountStatus;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
