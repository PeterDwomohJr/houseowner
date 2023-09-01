package com.houseowner.edge.controllers;


import com.houseowner.edge.dto.JWTTokenDTO;
import com.houseowner.edge.dto.JWTTokenResponseDTO;
import com.houseowner.edge.services.JWTTokenService;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
@EnableReactiveMethodSecurity(useAuthorizationManager = true)
@RequestMapping("api/v0/token")
public class JWTTokenController {

    private final JWTTokenService jwtTokenService;



    public JWTTokenController(JWTTokenService jwtTokenService) {
        this.jwtTokenService = jwtTokenService;
    }



    @PostMapping()
    public Flux<JWTTokenResponseDTO> generateJWTToken(@RequestBody JWTTokenDTO jwtTokenDTO)
    {
        return jwtTokenService.generateJWTToken(jwtTokenDTO);
    }


    @GetMapping()
    public Flux<String> getJWTAccessToken(@RequestBody JWTTokenDTO jwtTokenDTO)
    {
        return jwtTokenService.getJWTAccessToken(jwtTokenDTO);
    }


    @GetMapping("/refresh")
    public Flux<String> getJWTRefreshToken(@RequestBody JWTTokenDTO jwtTokenDTO)
    {
        return jwtTokenService.getJWTRefreshToken(jwtTokenDTO);
    }
}