package com.transactionorder.transactionorderservice.service;

import com.transactionorder.transactionorderservice.exception.TokenDetailsNotFound;
import com.transactionorder.transactionorderservice.model.TokenDetailsTO;
import com.transactionorder.transactionorderservice.model.ZelleTokenDetailsRequest;
import org.apache.el.parser.Token;

import java.util.List;

public interface ZelleTokenService {
    List<TokenDetailsTO> findAll() throws TokenDetailsNotFound;
    TokenDetailsTO findByID(String id) throws TokenDetailsNotFound;
    List<TokenDetailsTO> findByTokenType(String tokenType) throws TokenDetailsNotFound;
    List<TokenDetailsTO> findByTokenTypeandTokenValue(String tokenType,String tokenValue) throws TokenDetailsNotFound;
    void deleteById(String id) throws TokenDetailsNotFound;
    TokenDetailsTO saveTokenDetails(ZelleTokenDetailsRequest request)throws TokenDetailsNotFound;
    TokenDetailsTO updateTokenDetails(String id, ZelleTokenDetailsRequest request) throws TokenDetailsNotFound;
}
