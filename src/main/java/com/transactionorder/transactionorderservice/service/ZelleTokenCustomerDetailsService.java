package com.transactionorder.transactionorderservice.service;

import com.transactionorder.transactionorderservice.exception.TokenDetailsNotFound;
import com.transactionorder.transactionorderservice.model.TokenAndCustomerDetailsTO;
import com.transactionorder.transactionorderservice.model.TokenDetailsTO;


public interface ZelleTokenCustomerDetailsService {
    TokenAndCustomerDetailsTO findByID(String id) throws TokenDetailsNotFound;
}
