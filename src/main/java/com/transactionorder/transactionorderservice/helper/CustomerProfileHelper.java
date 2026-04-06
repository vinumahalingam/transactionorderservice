package com.transactionorder.transactionorderservice.helper;

import com.transactionorder.transactionorderservice.model.CustomerProfileDTO;
import com.transactionorder.transactionorderservice.model.CombinedCustomerProfileTO;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

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

    public Mono<CombinedCustomerProfileTO> getCombinedUsers(Long profileId1, Long profileId2) {
        Mono<CustomerProfileDTO> mono1 = webClient.get()
                .uri("/{id}", profileId1)
                .retrieve()
                .bodyToMono(CustomerProfileDTO.class);

        Mono<CustomerProfileDTO> mono2 = webClient.get()
                .uri("/{id}", profileId2)
                .retrieve()
                .bodyToMono(CustomerProfileDTO.class);

        return Mono.zip(mono1, mono2, CombinedCustomerProfileTO::new);
    }
}
