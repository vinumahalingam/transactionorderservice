package com.transactionorder.transactionorderservice.controller;

import com.transactionorder.transactionorderservice.exception.TokenDetailsNotFound;
import com.transactionorder.transactionorderservice.model.CombinedCustomerProfileTO;
import com.transactionorder.transactionorderservice.model.TokenAndCustomerDetailsTO;
import com.transactionorder.transactionorderservice.model.TokenDetailsTO;
import com.transactionorder.transactionorderservice.model.ZelleTokenDetailsRequest;
import com.transactionorder.transactionorderservice.service.ZelleTokenCustomerDetailsService;
import com.transactionorder.transactionorderservice.service.ZelleTokenService;
import com.transactionorder.transactionorderservice.exception.InvalidRequestException;
import com.transactionorder.transactionorderservice.exception.CustomerProfileNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@Slf4j
@RequestMapping("/api/v2/zelletokens")
@RestController
public class TokenAndCustomerController {
    @Autowired
    ZelleTokenCustomerDetailsService zelleTokenCustomerDetailsService;

    @GetMapping("/byid")
    public ResponseEntity<TokenAndCustomerDetailsTO> getTokenById(@RequestParam("id") String id)
            throws TokenDetailsNotFound {
        log.info("Inside ZelleTokenController - getTokenById for ID: " + id);
        TokenAndCustomerDetailsTO tokenAndCustomerDetailsTO = zelleTokenCustomerDetailsService.findByID(id);
        return ResponseEntity.ok(tokenAndCustomerDetailsTO);
    }

    @GetMapping("/combinedIds")
    public ResponseEntity<Mono<CombinedCustomerProfileTO>> getCombinedCustomerProfiles(
            @RequestParam("profileId1") @NotNull(message = "Profile Id cannot be 0 or negative") Long profileId1,
            @RequestParam("profileId2") @NotNull(message = "Profile Id cannot be 0 or negative") Long profileId2)
            throws InvalidRequestException, CustomerProfileNotFoundException {

        log.info("Inside TokenAndCustomerProfileDetailsController.getCombinedCustomerProfiles");
        Mono<CombinedCustomerProfileTO> combinedCustomerProfile = zelleTokenCustomerDetailsService
                .getCombinedCustomerProfile(profileId1, profileId2);
        return ResponseEntity.ok(combinedCustomerProfile);
    }

}
