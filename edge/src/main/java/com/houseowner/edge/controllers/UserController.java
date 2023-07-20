package com.houseowner.edge.controllers;

import com.houseowner.edge.dto.UserDTO;
import com.houseowner.edge.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.util.Map;

@RequestMapping("api/v0/user")
@EnableReactiveMethodSecurity(useAuthorizationManager = true)
@RestController
public class UserController {
    private final UserService userService;


    public UserController(UserService userService)
    {
        // inject the user service from the application context
        this.userService = userService;
    }



    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public Flux<UserDTO> getUsers()
    {
        // returns all the users
        return userService.getUsers();
    }



    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('user')")
    public Mono<UserDTO> getUser(@PathVariable String id)
    {
        // returns the user with the given id
        return userService.getUser(id);
    }


    @GetMapping("/userinfo")
    @PreAuthorize("hasAuthority('user')")
    public Mono<Map<String, Object>> getUserInfo(@AuthenticationPrincipal OAuth2User oAuth2User)
    {
        return Mono.just(oAuth2User.getAttributes());
    }



    @GetMapping("/count")
    @PreAuthorize("hasAuthority('admin')")
    public Mono<Long> getUserCount()
    {
        // returns the number of users in the repository
        return userService.getUserCount();
    }



    @GetMapping("/active")
    @PreAuthorize("hasAuthority('admin')")
    public Flux<UserDTO> getActiveUsers()
    {
        // returns all the users that are active in the repository
        return userService.getActiveUsers();
    }



    @GetMapping("/pending")
    @PreAuthorize("hasAuthority('admin')")
    public Flux<UserDTO> getPendingUsers()
    {
        // returns all the users that are pending in the repository
        return userService.getPendingUsers();
    }



    @GetMapping("soft-deleted")
    @PreAuthorize("hasAuthority('admin')")
    public Flux<UserDTO> getSoftDeletedUsers()
    {
        // returns all the users that are soft deleted
        return userService.getSoftDeletedUsers();
    }



    @GetMapping("/non-deleted")
    public Flux<UserDTO> getNonSoftDeletedUsers()
    {
        // returns all the users that are not soft deleted
        return userService.getNonSoftDeletedUsers();
    }
}
