package com.houseowner.edge.configuration;

import com.fasterxml.jackson.core.filter.TokenFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {


    @Bean
    public RouteLocator propertyRouteLocator(RouteLocatorBuilder routeLocatorBuilder)
    {
        return routeLocatorBuilder.routes()
                .route("property-service", resource -> resource.path("/api/v0/property/**")
                        .filters(filter -> filter.filter(tokenFilter()))
                        .uri("http://localhost:9090"))
                .build();

    }


    @Bean
    public GatewayFilter tokenFilter()
    {
        return new CustomTokenFilter();
    }
}
