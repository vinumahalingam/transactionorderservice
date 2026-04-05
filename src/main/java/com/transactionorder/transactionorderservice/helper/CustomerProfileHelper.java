package com.transactionorder.transactionorderservice.helper;

import com.transactionorder.transactionorderservice.model.CustomerProfileDTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class CustomerProfileHelper {
    private final WebClient webClient;


    public CustomerProfileHelper(WebClient webClient) {
        this.webClient = webClient;
    }

    public CustomerProfileDTO getUserById(Long id) {

        return webClient.get()
                .uri("/{id}", id)
                .retrieve()
                .bodyToMono(CustomerProfileDTO.class).block();
    }

    public Mono<CombinedCustomerProfileTO> getCombinedUsers(){

    }
}
