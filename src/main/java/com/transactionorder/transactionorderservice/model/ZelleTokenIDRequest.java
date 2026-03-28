package com.transactionorder.transactionorderservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
public class ZelleTokenIDRequest {
    @NotBlank
    public String tokenType;
    @NotBlank
    public String tokenValue;
}
