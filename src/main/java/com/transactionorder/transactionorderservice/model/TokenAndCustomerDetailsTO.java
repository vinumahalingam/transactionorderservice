package com.transactionorder.transactionorderservice.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.OffsetDateTime;

@Getter
@Setter
public class TokenAndCustomerDetailsTO {
    String tokenId;
    String tokenType;
    String tokenValue;
    Long customerId;
    String firstName;
    String lastName;
      String email;
      String phone;
      String addressLine1;
      String addressLine2;
      String city;
      String state;
      String postalCode;
      String country;
      LocalDate dateOfBirth;
      String gender;
      Boolean isActive;
      String accountStatus;
      OffsetDateTime createdAt;
      OffsetDateTime updatedAt;
}
