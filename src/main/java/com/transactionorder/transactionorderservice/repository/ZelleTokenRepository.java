package com.transactionorder.transactionorderservice.repository;

import com.transactionorder.transactionorderservice.entity.ZelleToken;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ZelleTokenRepository extends MongoRepository<ZelleToken,String> {
    List<ZelleToken> findByTokenType(String tokenType);
    List<ZelleToken> findByTokenTypeAndTokenValue(String tokenType, String tokenValue);
}
