package com.dwomo.otpdata.controllers;


import com.dwomo.otpdata.dto.OTPCreatedEventDTO;
import com.dwomo.otpdata.events.handlers.OTPEventHandler;
import com.dwomo.otpdata.services.OTPService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v0/otp")
public class OTPDataController {

    private final OTPService otpService;
    private final OTPEventHandler otpEventHandler;



    public OTPDataController(OTPService otpService, OTPEventHandler otpEventHandler)
    {
        this.otpService = otpService;
        this.otpEventHandler = otpEventHandler;
    }




    @GetMapping("/all")
    @PreAuthorize("hasAuthority('admin')")
    public Flux<OTPCreatedEventDTO> getOTPs()
    {
        return otpService.getOTPs();
    }



    @GetMapping("/receive")
    @PreAuthorize("hasAuthority('user')")
    public void receiveOTP()
    {
        otpEventHandler.receiveOTP();
    }


    @GetMapping("/count")
    @PreAuthorize("hasAuthority('admin')")
    public Mono<Long> getOTPCount()
    {
        return otpService.getCount();
    }



    @PostMapping()
    public Mono<OTPCreatedEventDTO> saveOTP(Mono<OTPCreatedEventDTO> otpCreatedEventDTOMono)
    {
        return otpService.saveOTP(otpCreatedEventDTOMono);
    }
}
