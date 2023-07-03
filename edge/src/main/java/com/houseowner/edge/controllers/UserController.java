package com.houseowner.edge.controllers;

import com.houseowner.edge.dto.UserDTO;
import com.houseowner.edge.services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;


    public UserController(UserService userService) {
        this.userService = userService;
    }



    @GetMapping()
    public Flux<UserDTO> getUsers()
    {
        return userService.getUsers();
    }



    @GetMapping("/{id}")
    public Mono<UserDTO> getUser(@PathVariable String id)
    {
        return userService.getUser(id);
    }



    @GetMapping("/count")
    public Mono<Long> getUserCount()
    {
        return userService.getUserCount();
    }



    @GetMapping("/active")
    public Flux<UserDTO> getActiveUsers()
    {
        return userService.getActiveUsers();
    }



    @GetMapping("/pending")
    public Flux<UserDTO> getPendingUsers()
    {
        return userService.getPendingUsers();
    }



    @GetMapping("soft-deleted")
    public Flux<UserDTO> getSoftDeletedUsers()
    {
        return userService.getSoftDeletedUsers();
    }



    @GetMapping("/non-deleted")
    public Flux<UserDTO> getNonSoftDeletedUsers()
    {
        return userService.getNonSoftDeletedUsers();
    }

}
