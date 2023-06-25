package com.houseowner.edge.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.HttpStatusServerEntryPoint;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;
import org.springframework.security.web.server.csrf.CookieServerCsrfTokenRepository;
import org.springframework.security.web.server.csrf.CsrfToken;
import org.springframework.web.server.WebFilter;
import reactor.core.publisher.Mono;

@EnableWebFluxSecurity
public class SecurityConfiguration {


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(
            ServerHttpSecurity httpSecurity, ReactiveClientRegistrationRepository registrationRepository)
    {
        return httpSecurity
                .authorizeExchange(exchange ->
                         exchange.pathMatchers(HttpMethod.GET, "/properties").permitAll()
                                 .pathMatchers("/").permitAll()
                                 .anyExchange().authenticated())
                                 .exceptionHandling(exceptionHandling -> exceptionHandling.authenticationEntryPoint(new HttpStatusServerEntryPoint(HttpStatus.UNAUTHORIZED)))

                .oauth2Login(Customizer.withDefaults())

                .logout(logout -> logout.logoutSuccessHandler(oidcLogoutSuccessHandler(registrationRepository)))

                // uses a cookie-based strategy for exchanging CSRF tokens with the frontend
                .csrf(csrf -> csrf.csrfTokenRepository(CookieServerCsrfTokenRepository.withHttpOnlyFalse()))
                .build();
    }


    private ServerLogoutSuccessHandler oidcLogoutSuccessHandler(ReactiveClientRegistrationRepository registrationRepository)
    {
        var oidcLogoutSuccessHandler = new OidcClientInitiatedServerLogoutSuccessHandler(registrationRepository);
        oidcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}");
        return oidcLogoutSuccessHandler;
    }


    /**
     * subscribe to the CsrfToken reactive stream and ensuring its value is extracted correctly
     * @return
     */
    @Bean
    public WebFilter csrfWebFilter()
    {
        return (exchange, chain) -> {
            exchange.getResponse().beforeCommit(() -> Mono.defer(() -> {
                Mono<CsrfToken> csrfToken = exchange.getAttribute(CsrfToken.class.getName());

                return csrfToken != null ? csrfToken.then() : Mono.empty();

            }));

            return chain.filter(exchange);
        };
    }
}
