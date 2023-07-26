package com.houseowner.edge.configuration;

import com.fasterxml.jackson.core.filter.TokenFilter;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {
    private static final String PROPERTY_ID = "property-service";
    private static final String PROPERTY_PATH = "/api/v0/property/**";
    private static final String PROPERTY_HOST = "http://localhost:9090";
    private static final String OTP_DATA_ID = "otp-data-service";
    private static final String OTP_DATA_PATH = "/api/v0/otp/**";
    private static final String OTP_DATA_HOST = "http://localhost:9094";
    private static final String USER_DATA_ID = "user-data-service";
    private static final String USER_DATA_PATH = "/api/v0/user/**";
    private static final String USER_DATA_HOST = "http://localhost:9095";

    @Bean
    public RouteLocator propertyRouteLocator(RouteLocatorBuilder routeLocatorBuilder)
    {
        return routeLocatorBuilder.routes()
                .route(PROPERTY_ID, resource -> resource.path(PROPERTY_PATH)
                        .filters(filter -> filter.filter(tokenFilter()))
                        .uri(PROPERTY_HOST))
                .route(OTP_DATA_ID, resource -> resource.path(OTP_DATA_PATH)
                        .filters(filter -> filter.filter(tokenFilter()))
                        .uri(OTP_DATA_HOST))
                .route(USER_DATA_ID, resource -> resource.path(USER_DATA_PATH)
                        .filters(filter -> filter.filter(tokenFilter()))
                        .uri(USER_DATA_HOST))
                .build();

    }


    @Bean
    public GatewayFilter tokenFilter()
    {
        return new CustomTokenFilter();
    }
}
