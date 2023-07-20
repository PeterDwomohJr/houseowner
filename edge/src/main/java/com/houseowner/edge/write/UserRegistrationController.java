package com.houseowner.edge.write;


import com.houseowner.edge.dto.RegisterUserDTO;
import com.houseowner.edge.services.UserRegistrationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/v0/keycloak")
public class UserRegistrationController {

    private final UserRegistrationService userRegistrationService;


    public UserRegistrationController(UserRegistrationService userRegistrationService)
    {
        this.userRegistrationService = userRegistrationService;
    }

    @PostMapping("/user/register")
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<String> registerKeycloakUser(@RequestBody RegisterUserDTO user, @RequestParam("bearerToken") String bearerToken)
    {
        return userRegistrationService.registerKeycloakUser(user, bearerToken);
    }
}
