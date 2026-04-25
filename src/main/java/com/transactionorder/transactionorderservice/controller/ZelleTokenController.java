package com.transactionorder.transactionorderservice.controller;

import com.transactionorder.transactionorderservice.exception.TokenDetailsNotFound;
import com.transactionorder.transactionorderservice.model.TokenDetailsTO;
import com.transactionorder.transactionorderservice.model.ZelleTokenDetailsRequest;
import com.transactionorder.transactionorderservice.service.ZelleTokenService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/api/v1/zelletokens")
@RestController
public class ZelleTokenController {
    @Autowired
    ZelleTokenService zelleTokenService;

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public ResponseEntity<List<TokenDetailsTO>> getAllTokens() throws TokenDetailsNotFound {
        log.info("Inside the ZelleTokenController.getAllTokens");
        List<TokenDetailsTO> list=zelleTokenService.findAll();
        log.info("Count of tokens "+list.size());
        return ResponseEntity.ok(list);
    }

    @GetMapping("/byid")
    public ResponseEntity<TokenDetailsTO> getTokenById(@RequestParam("id") String id) throws TokenDetailsNotFound {
        log.info("Inside ZelleTokenController - getTokenById for ID: " + id);
        TokenDetailsTO tokenDetailsDTO = zelleTokenService.findByID(id);
        return ResponseEntity.ok(tokenDetailsDTO);
    }

    @GetMapping("/bytokenType")
    public ResponseEntity<List<TokenDetailsTO>> getTokenByType(@RequestParam("tokenType") String tokenType) throws TokenDetailsNotFound {
        log.info("Inside ZelleTokenController - getTokenByType for TokenType: " + tokenType);
        List<TokenDetailsTO> list = zelleTokenService.findByTokenType(tokenType);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/bytokenTypeandtokenValue")
    public ResponseEntity<List<TokenDetailsTO>> getTokenByTypeandtokenValue(@RequestParam("tokenType") String tokenType,@RequestParam("tokenValue") String tokenValue) throws TokenDetailsNotFound {
        log.info("Inside ZelleTokenController - getTokenByType for TokenType {} and Token Value {}: ", tokenType, tokenValue);
        List<TokenDetailsTO> list = zelleTokenService.findByTokenTypeandTokenValue(tokenType, tokenValue);
        return ResponseEntity.ok(list);
    }

    @DeleteMapping("/deleteById")
    public ResponseEntity<String> deleteById(@RequestParam("id") String id) throws TokenDetailsNotFound {
        log.info("Inside ZelleTokenController - deleteById for ID: " + id);
        zelleTokenService.deleteById(id);
        return ResponseEntity.ok("Record deleted");
    }

    @PostMapping
    public ResponseEntity<TokenDetailsTO> saveTokenDetails(@RequestBody @Valid ZelleTokenDetailsRequest zelleTokenDetailsRequest) throws TokenDetailsNotFound{
        log.info("Throwing error for saveTokenDetails" + zelleTokenDetailsRequest.getTokenValue() + " "+ zelleTokenDetailsRequest.getTokenType());
        TokenDetailsTO tokenDetailsTO = zelleTokenService.saveTokenDetails(zelleTokenDetailsRequest);
        return ResponseEntity.ok(tokenDetailsTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TokenDetailsTO> updateTokenDetails(@PathVariable("id") String id, @RequestBody @Valid ZelleTokenDetailsRequest zelleTokenDetailsRequest) throws TokenDetailsNotFound {
        log.info("Updating token details for ID: {}", id);
        TokenDetailsTO tokenDetailsTO = zelleTokenService.updateTokenDetails(id, zelleTokenDetailsRequest);
        return ResponseEntity.ok(tokenDetailsTO);
    }


}
