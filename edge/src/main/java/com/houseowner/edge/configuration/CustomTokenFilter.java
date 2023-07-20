package com.houseowner.edge.configuration;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class CustomTokenFilter implements GatewayFilter, Ordered {

    private static final String AUTHORIZATION_HEADER = "Authorization";
    private static final String BEARER_PREFIX = "Bearer ";


    /**
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpRequest request = exchange.getRequest();
        String token = extractTokenFromRequest(exchange.getRequest());

        boolean tokenIsNull = token == null;

        if (tokenIsNull) {
            // Token not found in the request, return unauthorized status
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete();
        }

        ServerHttpRequest modifiedRequest = request.mutate()
                .header(AUTHORIZATION_HEADER, BEARER_PREFIX + token)
                .build();

        ServerWebExchange modifiedExchange = exchange.mutate().request(modifiedRequest).build();
        return chain.filter(modifiedExchange);
    }

    private String extractTokenFromRequest(ServerHttpRequest request) {

        String authorizationHeader = request.getHeaders().getFirst(AUTHORIZATION_HEADER);

        if (authorizationHeader != null && authorizationHeader.startsWith(BEARER_PREFIX)) {
            // Extract the token after "Bearer "
            return authorizationHeader.substring(BEARER_PREFIX.length());
        }
        return null;
    }

    /**
     */
    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
