package com.houseowner.edge.services;

import com.houseowner.edge.aggregates.entities.User;
import com.houseowner.edge.aggregates.entities.UserRecord;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class UserService {

    public Mono<UserRecord> getUser(@AuthenticationPrincipal OidcUser oidcUser)
    {
        var user = new UserRecord(
                oidcUser.getPreferredUsername(),
                oidcUser.getFullName(),
                oidcUser.getFamilyName(),
                List.of("employee", "customer")
        );

        return Mono.just(user);
    }
}