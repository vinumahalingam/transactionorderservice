package com.transactionorder.transactionorderservice.service;

import com.transactionorder.transactionorderservice.exception.TokenDetailsNotFound;
import com.transactionorder.transactionorderservice.model.TokenAndCustomerDetailsTO;
import com.transactionorder.transactionorderservice.model.TokenDetailsTO;
import com.transactionorder.transactionorderservice.exception.CustomerProfileNotFoundException;
import com.transactionorder.transactionorderservice.exception.InvalidRequestException;
import com.transactionorder.transactionorderservice.model.CombinedCustomerProfileTO;
import reactor.core.publisher.Mono;

public interface ZelleTokenCustomerDetailsService {
    TokenAndCustomerDetailsTO findByID(String id) throws TokenDetailsNotFound;
    Mono<CombinedCustomerProfileTO> getCombinedCustomerProfile(Long profileId1, Long profileId2) throws InvalidRequestException, CustomerProfileNotFoundException;
}
