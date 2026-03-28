package com.transactionorder.transactionorderservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenDetailsTO {
    String id;
    String tokenType;
    String tokenValue;
}
