package com.houseowner.edge.controllers;


import com.houseowner.edge.dto.OTPCreatedEventDTO;
import com.houseowner.edge.dto.ConsumeTopicRequestDTO;
import com.houseowner.edge.events.handlers.OTPEventHandler;
import com.houseowner.edge.services.OTPRepositoryService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@EnableReactiveMethodSecurity(useAuthorizationManager = true)
@RequestMapping("/api/v0/cache-otp")
public class OTPRepositoryController {


    private final OTPRepositoryService otpRepositoryService;
    private final OTPEventHandler otpEventHandler;



    public OTPRepositoryController(OTPRepositoryService otpRepositoryService, OTPEventHandler otpEventHandler)
    {
        this.otpRepositoryService = otpRepositoryService;
        this.otpEventHandler = otpEventHandler;
    }



    @GetMapping()
    public Flux<OTPCreatedEventDTO> getOTPs()
    {
       return otpRepositoryService.getOTPs();
    }



    @PostMapping("/consume")
    public void consumeTopic(@RequestBody ConsumeTopicRequestDTO consumeTopicRequestDTO)
    {
        otpEventHandler.consumeTopic(consumeTopicRequestDTO);
    }


    @GetMapping("/count")
    @PreAuthorize("hasAuthority('user')")
    public Mono<Long> getOTPCount()
    {
        return otpRepositoryService.getCount();
    }
}
