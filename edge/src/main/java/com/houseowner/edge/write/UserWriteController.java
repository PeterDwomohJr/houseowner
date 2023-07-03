package com.houseowner.edge.write;


import com.houseowner.edge.dto.UserDTO;
import com.houseowner.edge.services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserWriteController {

    private final UserService userService;


    public UserWriteController(UserService userService) {
        this.userService = userService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping()
    public Mono<UserDTO> saveUser(@RequestBody Mono<UserDTO> user)
    {
        return userService.saveUser(user);
    }


    @PutMapping()
    public Mono<UserDTO> updateUser(@PathVariable String id, @RequestBody Mono<UserDTO> user)
    {
        return userService.updateUser(id, user);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public Mono<Void> deleteUser(@PathVariable String id)
    {
        return userService.deleteUser(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/soft")
    public Mono<Void> softDeleteUser(@PathVariable String id)
    {
        return userService.softDeleteUser(id);
    }


    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping()
    public Mono<Void> deleteAllUsers()
    {
        return userService.deleteAllUsers();
    }


    @PatchMapping("/{id}")
    public Mono<UserDTO> setUserStatus(@PathVariable String id, @RequestParam String status)
    {
        return userService.setUserStatus(id, status);
    }
}
