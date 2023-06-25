package com.houseowner.edge.controllers;

import com.houseowner.edge.aggregates.entities.UserRecord;
import com.houseowner.edge.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @GetMapping("/")
    public Mono<UserRecord> getHomePage(@AuthenticationPrincipal OidcUser oidcUser)
    {
        return userService.getUser(oidcUser);
    }
}
