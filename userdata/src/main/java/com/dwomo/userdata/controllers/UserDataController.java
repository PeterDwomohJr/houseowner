package com.dwomo.userdata.controllers;


import com.dwomo.userdata.dto.UserCreatedEventDTO;
import com.dwomo.userdata.events.handlers.UserEventHandler;
import com.dwomo.userdata.services.UserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v0/otp")
public class UserDataController {

    private final UserService userService;
    private final UserEventHandler userEventHandler;



    public UserDataController(UserService userService, UserEventHandler userEventHandler)
    {
        this.userService = userService;
        this.userEventHandler = userEventHandler;
    }




    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public Flux<UserCreatedEventDTO> getOTPs()
    {
        return userService.getOTPs();
    }



    @GetMapping("/receive")
    @PreAuthorize("hasAuthority('user')")
    public void receiveOTP()
    {
        userEventHandler.receiveOTP();
    }


    @GetMapping("/count")
    @PreAuthorize("hasAuthority('admin')")
    public Mono<Long> getOTPCount()
    {
        return userService.getCount();
    }



    @PostMapping()
    public Mono<UserCreatedEventDTO> saveOTP(Mono<UserCreatedEventDTO> otpCreatedEventDTOMono)
    {
        return userService.saveOTP(otpCreatedEventDTOMono);
    }
}
