package com.houseowner.edge.write;


import com.houseowner.edge.dto.UserCreatedEventDTO;
import com.houseowner.edge.services.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RequestMapping("api/v0/cache-user")
@EnableReactiveMethodSecurity(useAuthorizationManager = true)
@RestController
public class UserWriteController {

    private final UserService userService;


    public UserWriteController(UserService userService) {
        // inject the user service from the application context
        this.userService = userService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Mono<UserCreatedEventDTO> saveUser(@Valid @RequestBody Mono<UserCreatedEventDTO> user)
    {
        // save the user
        return userService.save(user);
    }


    @PreAuthorize("hasAuthority('user')")
    @PutMapping()
    public Mono<UserCreatedEventDTO> updateUser(@PathVariable String id, @Valid @RequestBody Mono<UserCreatedEventDTO> user)
    {
        // update the user with the given id
        return userService.update(id, user);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('user')")
    @DeleteMapping("/{id}")
    public Mono<Void> deleteUser(@PathVariable String id)
    {
        // delete the user with the given id
        return userService.delete(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping("/soft")
    public Mono<Void> softDeleteUser(@PathVariable String id)
    {
        // soft delete the user with the given id
        return userService.softDelete(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    @DeleteMapping()
    public Mono<Void> deleteAllUsers()
    {
        // deletes all users
        return userService.deleteAll();
    }


    @PreAuthorize("hasAuthority('user')")
    @PatchMapping("/{id}")
    public Mono<UserCreatedEventDTO> setUserStatus(@PathVariable String id, @RequestParam String status)
    {
        // set the user status
        return userService.setUserStatus(id, status);
    }
}
