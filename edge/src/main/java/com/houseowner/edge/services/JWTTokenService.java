package com.houseowner.edge.services;

import com.houseowner.edge.builders.DTOBuilder;
import com.houseowner.edge.dto.JWTTokenDTO;
import com.houseowner.edge.dto.JWTTokenResponseDTO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@Service
public class JWTTokenService {

    private static final String GRANT_TYPE = "grant_type";
    private static final String GRANT_TYPE_VALUE = "password";
    private static final String CLIENT_ID = "client_id";
    private static final String CLIENT_ID_VALUE = "EdgeApplication";
    private static final String USERNAME = "username";
    private static final String PASSWORD = "password";
    private static final String KEYCLOAK_TOKEN_URL = "http://localhost:8888/realms/houseowner/protocol/openid-connect/token";


    public Flux<JWTTokenResponseDTO> generateJWTToken(DTOBuilder jwtTokenDTO) {

        WebClient client = WebClient.builder()
                .baseUrl(KEYCLOAK_TOKEN_URL)
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_FORM_URLENCODED_VALUE)
                .build();

        return client.post()
                .uri(KEYCLOAK_TOKEN_URL)
                .body(BodyInserters.fromFormData(
                        GRANT_TYPE, GRANT_TYPE_VALUE)
                        .with(CLIENT_ID, CLIENT_ID_VALUE)
                        .with(USERNAME, jwtTokenDTO.getUsername())
                        .with(PASSWORD, jwtTokenDTO.getPassword()))
                .retrieve()
                .bodyToFlux(JWTTokenResponseDTO.class);
    }


    public Flux<String> getJWTAccessToken(DTOBuilder jwtTokenDTO)
    {
        Flux<JWTTokenResponseDTO> jwtTokenResponse = generateJWTToken(jwtTokenDTO);
        return jwtTokenResponse.map(JWTTokenResponseDTO::getAccess_token);
    }


    public Flux<String> getJWTRefreshToken(DTOBuilder jwtTokenDTO)
    {
        Flux<JWTTokenResponseDTO> jwtTokenResponse = generateJWTToken(jwtTokenDTO);
        return jwtTokenResponse.map(JWTTokenResponseDTO::getRefresh_token);
    }
}
