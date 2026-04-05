package com.transactionorder.transactionorderservice.service;

import com.transactionorder.transactionorderservice.entity.ZelleToken;
import com.transactionorder.transactionorderservice.exception.TokenDetailsNotFound;
import com.transactionorder.transactionorderservice.helper.CustomerProfileHelper;
import com.transactionorder.transactionorderservice.model.CustomerProfileDTO;
import com.transactionorder.transactionorderservice.model.TokenAndCustomerDetailsTO;
import com.transactionorder.transactionorderservice.model.TokenDetailsTO;
import com.transactionorder.transactionorderservice.repository.ZelleTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ZelleTokenCustomerDetailsServiceImpl implements ZelleTokenCustomerDetailsService{
    @Autowired
    ZelleTokenRepository zelleTokenRepository;
    @Autowired
    CustomerProfileHelper customerProfileHelper;

    public TokenAndCustomerDetailsTO findByID(String ID) throws TokenDetailsNotFound {
        ZelleToken zelleToken = zelleTokenRepository.findById(ID).orElse(null);
        if (zelleToken == null) {
            log.error("No token details found for ID: " + ID);
            throw new TokenDetailsNotFound("No token details found for ID: " + ID);
        }
        CustomerProfileDTO customerProfileDTO=customerProfileHelper.getUserById(zelleToken.getCustomerProfileId());
        TokenAndCustomerDetailsTO tokenAndCustomerDetailsTO = new TokenAndCustomerDetailsTO();
        tokenAndCustomerDetailsTO.setTokenId(zelleToken.getId());
        tokenAndCustomerDetailsTO.setTokenValue(zelleToken.getTokenValue());
        tokenAndCustomerDetailsTO.setTokenType(zelleToken.getTokenType());
        tokenAndCustomerDetailsTO.setFirstName(customerProfileDTO.getFirstName());
        tokenAndCustomerDetailsTO.setLastName(customerProfileDTO.getLastName());
        tokenAndCustomerDetailsTO.setEmail(customerProfileDTO.getEmail());
        tokenAndCustomerDetailsTO.setPhone(customerProfileDTO.getPhone());
        tokenAndCustomerDetailsTO.setAddressLine1(customerProfileDTO.getAddressLine1());
        tokenAndCustomerDetailsTO.setAddressLine2( customerProfileDTO.getAddressLine2());
        tokenAndCustomerDetailsTO.setCity( customerProfileDTO.getCity());
        tokenAndCustomerDetailsTO.setState( customerProfileDTO.getState());
        tokenAndCustomerDetailsTO.setPostalCode( customerProfileDTO.getPostalCode());
        tokenAndCustomerDetailsTO.setCountry( customerProfileDTO.getCountry() != null ?  customerProfileDTO.getCountry() : "USA");
        tokenAndCustomerDetailsTO.setDateOfBirth( customerProfileDTO.getDateOfBirth());
        tokenAndCustomerDetailsTO.setGender( customerProfileDTO.getGender());
        tokenAndCustomerDetailsTO.setIsActive( customerProfileDTO.getIsActive() != null ?  customerProfileDTO.getIsActive() : true);
        return tokenAndCustomerDetailsTO;
        
    }




}
