package com.houseowner.edge.integration;


import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class OTPControllerIntegrationTest {


    private final WebTestClient webTestClient;




    public OTPControllerIntegrationTest(WebTestClient webTestClient)
    {
        this.webTestClient = webTestClient;
    }


}
