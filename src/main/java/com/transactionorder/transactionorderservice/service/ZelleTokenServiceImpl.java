package com.transactionorder.transactionorderservice.service;

import com.transactionorder.transactionorderservice.entity.ZelleToken;
import com.transactionorder.transactionorderservice.exception.TokenDetailsNotFound;
import com.transactionorder.transactionorderservice.model.TokenDetailsTO;
import com.transactionorder.transactionorderservice.model.ZelleTokenDetailsRequest;
import com.transactionorder.transactionorderservice.model.ZelleTokenIDRequest;
import com.transactionorder.transactionorderservice.repository.ZelleTokenRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Service
public class ZelleTokenServiceImpl implements ZelleTokenService{
    @Autowired
    ZelleTokenRepository zelleTokenRepository;

    @Override
    public List<TokenDetailsTO> findAll() throws TokenDetailsNotFound {
        log.info("Inside ZelleTokenServiceImpl.findAll");
        List<ZelleToken> list=zelleTokenRepository.findAll();
        if(CollectionUtils.isEmpty(list)){
            log.error("Zelle token not accessed");
            throw   new TokenDetailsNotFound("Zelle token not accessed");
        }
        return list.stream().map(zelleToken -> {
            TokenDetailsTO tokenDetailsTO=new TokenDetailsTO();
            tokenDetailsTO.setId(zelleToken.getId());
            tokenDetailsTO.setTokenValue(zelleToken.getTokenValue());
            tokenDetailsTO.setTokenType(zelleToken.getTokenType());
            return tokenDetailsTO;
        }).collect(Collectors.toList());
    }

    public TokenDetailsTO findByID(String ID) throws TokenDetailsNotFound {
        ZelleToken zelleToken = zelleTokenRepository.findById(ID).orElse(null);
        if (zelleToken == null) {
            log.error("No token details found for ID: " + ID);
            throw new TokenDetailsNotFound("No token details found for ID: " + ID);
        }
        TokenDetailsTO tokenDetailsTO = new TokenDetailsTO();
        tokenDetailsTO.setId(zelleToken.getId());
        tokenDetailsTO.setTokenValue(zelleToken.getTokenValue());
        tokenDetailsTO.setTokenType(zelleToken.getTokenType());
        return tokenDetailsTO;
    }

    public List<TokenDetailsTO> findByTokenType(String tokenType) throws TokenDetailsNotFound {
        List<ZelleToken> zelleTokens = zelleTokenRepository.findByTokenType(tokenType);
        log.info("Inside ZelleTokenServiceImpl - Browse by TokenType");
        if (CollectionUtils.isEmpty(zelleTokens)) {
            log.error("No token details found in the database");
            throw new TokenDetailsNotFound("No token details found in the database");
        }
        return zelleTokens.stream().map(ZelleToken -> {
            TokenDetailsTO tokenDetailsTO = new TokenDetailsTO();
            tokenDetailsTO.setId(ZelleToken.getId());
            tokenDetailsTO.setTokenValue(ZelleToken.getTokenValue());
            tokenDetailsTO.setTokenType(ZelleToken.getTokenType());
            return tokenDetailsTO;
        }).collect(Collectors.toList());
    }

    public List<TokenDetailsTO> findByTokenTypeandTokenValue(String tokenType,String tokenValue) throws TokenDetailsNotFound {
        List<ZelleToken> zelleTokens = zelleTokenRepository.findByTokenTypeAndTokenValue(tokenType,tokenValue);
        log.info("Inside ZelleTokenServiceImpl - Browse by TokenType and TokenValue");
        if (CollectionUtils.isEmpty(zelleTokens)) {
            log.error("No token details found in the database");
            throw new TokenDetailsNotFound("No token details found in the database");
        }
        return zelleTokens.stream().map(ZelleToken -> {
            TokenDetailsTO tokenDetailsTO = new TokenDetailsTO();
            tokenDetailsTO.setId(ZelleToken.getId());
            tokenDetailsTO.setTokenValue(ZelleToken.getTokenValue());
            tokenDetailsTO.setTokenType(ZelleToken.getTokenType());
            return tokenDetailsTO;
        }).collect(Collectors.toList());
    }

    public void deleteById(String ID) throws TokenDetailsNotFound {
        ZelleToken zelleToken = zelleTokenRepository.findById(ID).orElse(null);
        if (zelleToken == null) {
            log.error("No token details found for ID: " + ID);
            throw new TokenDetailsNotFound("No token details found for ID: " + ID);
        }
        zelleTokenRepository.deleteById(ID);
    }

    public TokenDetailsTO saveTokenDetails(ZelleTokenDetailsRequest request)throws TokenDetailsNotFound{
       log.info(" logging "+request.getTokenType() +" "+request.getTokenValue());
       ZelleToken zelleToken=new ZelleToken();
       zelleToken.setTokenValue(request.getTokenValue());
        zelleToken.setTokenType(request.getTokenType());
        ZelleToken savedToken=zelleTokenRepository.save(zelleToken);
        if (Objects.isNull(savedToken)) {
            log.error("Failed to save token details for token type: {} and token value: {}", request.getTokenType(), request.getTokenValue());
            throw new TokenDetailsNotFound("Failed to save token details for token type: " + request.getTokenType() + " and token value: " + request.getTokenValue());
        }
        TokenDetailsTO tokenDetailsTO = new TokenDetailsTO();
        tokenDetailsTO.setId(zelleToken.getId());
        tokenDetailsTO.setTokenValue(zelleToken.getTokenValue());
        tokenDetailsTO.setTokenType(zelleToken.getTokenType());
        return tokenDetailsTO;
    }

    public TokenDetailsTO updateTokenDetails(String id, ZelleTokenDetailsRequest request) throws TokenDetailsNotFound {
        log.info("Updating token details for ID: {}", id);
        ZelleToken existingToken = zelleTokenRepository.findById(id).orElse(null);
        if (existingToken == null) {
            log.error("No token details found for ID: {}", id);
            throw new TokenDetailsNotFound("No token details found for ID: " + id);
        }
        existingToken.setTokenValue(request.getTokenValue());
        existingToken.setTokenType(request.getTokenType());
        ZelleToken updatedToken = zelleTokenRepository.save(existingToken);
        if (Objects.isNull(updatedToken)) {
            log.error("Failed to update token details for ID: {}", id);
            throw new TokenDetailsNotFound("Failed to update token details for ID: " + id);
        }
        TokenDetailsTO tokenDetailsTO = new TokenDetailsTO();
        tokenDetailsTO.setId(updatedToken.getId());
        tokenDetailsTO.setTokenValue(updatedToken.getTokenValue());
        tokenDetailsTO.setTokenType(updatedToken.getTokenType());
        return tokenDetailsTO;
    }

    

}

