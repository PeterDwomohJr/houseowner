package com.dwomo.userdata.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authorization.ServerAccessDeniedHandler;
import org.springframework.security.web.server.context.NoOpServerSecurityContextRepository;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import reactor.core.publisher.Mono;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
@Configuration
public class SecurityConfig {

    private static final String USER_DATA_PATH = "/api/v0/otp/**";
    private static final String USER_ROLE = "user";
    private static final String[] ORIGINS = new String[]{"http://localhost:9094"};

    @Bean
    SecurityWebFilterChain filterChain(ServerHttpSecurity http, SpringAddonsJwtAuthenticationConverter authenticationConverter) throws Exception {

            http.oauth2ResourceServer(resourceServer -> resourceServer.jwt(jwtConf -> jwtConf.jwtAuthenticationConverter(authenticationConverter)));

            http.cors(cors -> cors.configurationSource(corsConfigurationSource()));

            // State-less session (state in access token only)
            http.securityContextRepository(NoOpServerSecurityContextRepository.getInstance());

            // Disable CSRF because of state-less session-management
            http.csrf(ServerHttpSecurity.CsrfSpec::disable);

            // Return 401 (unauthorized) instead of 302 (redirect to login) when
            // authorization is missing or invalid
            http.exceptionHandling(exceptionHandling -> {
                exceptionHandling.accessDeniedHandler(accessDeniedHandler());
            });

            // If SSL enabled, disable http (https only)


            http.authorizeExchange(exchange ->
                    exchange
                            .pathMatchers(USER_DATA_PATH).hasAuthority(USER_ROLE));

            return http.build();
        }



    private UrlBasedCorsConfigurationSource corsConfigurationSource() {
        final var configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList(SecurityConfig.ORIGINS));
        configuration.setAllowedMethods(List.of("*"));
        configuration.setAllowedHeaders(List.of("*"));
        configuration.setExposedHeaders(List.of("*"));

        final var source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

    private ServerAccessDeniedHandler accessDeniedHandler() {
        return (var exchange, var ex) -> exchange.getPrincipal().flatMap(principal -> {
            var response = exchange.getResponse();
            response.setStatusCode(principal instanceof AnonymousAuthenticationToken ? HttpStatus.UNAUTHORIZED : HttpStatus.FORBIDDEN);
            response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
            var dataBufferFactory = response.bufferFactory();
            var buffer = dataBufferFactory.wrap(ex.getMessage().getBytes(Charset.defaultCharset()));
            return response.writeWith(Mono.just(buffer)).doOnError(error -> DataBufferUtils.release(buffer));
        });
    }
}
