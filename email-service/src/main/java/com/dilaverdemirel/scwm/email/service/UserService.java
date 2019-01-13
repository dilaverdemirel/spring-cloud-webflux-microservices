package com.dilaverdemirel.scwm.email.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * @author dilaverd
 */
@Service
public class UserService {
    @Autowired
    private WebClient.Builder webClientBuilder;

    public Mono<ClientResponse> getUserList() {
        return webClientBuilder.build().get().uri("http://user-service/api/users").exchange();
    }
}
