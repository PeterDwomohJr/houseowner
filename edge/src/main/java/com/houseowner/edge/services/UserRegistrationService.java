package com.houseowner.edge.services;

import com.houseowner.edge.dto.RegisterUserDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class UserRegistrationService {

    private static final String KEYCLOAK_USER_REGISTER_URI = "http://localhost:8888/admin/realms/houseowner/users";


    public Mono<String> registerKeycloakUser(RegisterUserDTO user, String bearerToken) {

        // Create WebClient
        WebClient client = WebClient.builder()
                .baseUrl(KEYCLOAK_USER_REGISTER_URI)
                .defaultHeader(HttpHeaders.AUTHORIZATION, "Bearer " + bearerToken)
                .build();

        // Make the POST request to register the user
       return client.post()
                 .uri(KEYCLOAK_USER_REGISTER_URI)
                 .contentType(MediaType.APPLICATION_JSON)
                 .body(BodyInserters.fromValue(user))
                 .retrieve()
                 .bodyToMono(String.class);

    }
}
