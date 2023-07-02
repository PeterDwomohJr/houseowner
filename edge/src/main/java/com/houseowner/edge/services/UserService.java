package com.houseowner.edge.services;

import com.houseowner.edge.dto.UserDTO;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    public Mono<UserDTO> getUser(@AuthenticationPrincipal OidcUser oidcUser)
    {
        var user = new UserDTO(
                oidcUser.getPreferredUsername(),
                oidcUser.getFullName()
        );

        return Mono.just(user);
    }
}