package com.houseowner.edge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

public class SecurityConfiguration {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity)
    {
        return httpSecurity
                .authorizeExchange(exchange ->
                        exchange.anyExchange().authenticated())
                .build();
    }
}
