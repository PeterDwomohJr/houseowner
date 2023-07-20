package com.dwomo.houseowner.configuration;

import com.jayway.jsonpath.JsonPath;
import lombok.RequiredArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class SpringAddonsJwtAuthenticationConverter implements Converter<Jwt, Mono<? extends AbstractAuthenticationToken>> {
    private final SpringAddonsProperties springAddonsProperties;

    @Override
    public Mono<? extends AbstractAuthenticationToken> convert(Jwt jwt) {
        final var issuerProperties = springAddonsProperties.get(jwt.getIssuer());
        final var authorities = new JwtGrantedAuthoritiesConverter(issuerProperties).convert(jwt);
        final String username = JsonPath.read(jwt.getClaims(), issuerProperties.getUsernameJsonPath());
        return Mono.just(new JwtAuthenticationToken(jwt, authorities, username));
    }
}
