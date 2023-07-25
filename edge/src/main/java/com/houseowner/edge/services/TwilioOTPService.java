package com.houseowner.edge.services;

import com.houseowner.edge.dto.OTPDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TwilioOTPService {

    private final OTPService otpService;


    public TwilioOTPService(OTPService otpService)
    {
        // inject the OTPService
        this.otpService = otpService;
    }


    public Mono<ServerResponse> sendOTP(ServerRequest serverRequest)
    {

        return serverRequest.bodyToMono(OTPDTO.class)
                .flatMap(otpService::sendOTP)
                .flatMap(otpDTO -> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(otpDTO)));
    }


    public Mono<ServerResponse> validateOTP(ServerRequest serverRequest)
    {
        return serverRequest.bodyToMono(OTPDTO.class)
                .flatMap(otpDTO -> otpService.validateOTP(otpDTO.getPhoneNumber(), otpDTO.getOtpString()))
                .flatMap(otpDTO -> ServerResponse.status(HttpStatus.OK).bodyValue(otpDTO));
    }
}
