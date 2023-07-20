package com.houseowner.edge.write;

import com.houseowner.edge.dto.OneTimePasswordDTO;
import com.houseowner.edge.services.OTPService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class TwilioOTPHandler {

    private final OTPService otpService;


    public TwilioOTPHandler(OTPService otpService)
    {
        this.otpService = otpService;
    }


    public Mono<ServerResponse> sendOTP(ServerRequest serverRequest)
    {
        return serverRequest.bodyToMono(OneTimePasswordDTO.class)
                .flatMap(otpService::sendOTP)
                .flatMap(otpDTO -> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(otpDTO)));
    }


    public Mono<ServerResponse> validateOTP(ServerRequest serverRequest)
    {
        return serverRequest.bodyToMono(OneTimePasswordDTO.class)
                .flatMap(otpService::validateOTP)
                .flatMap(otpDTO -> ServerResponse.status(HttpStatus.OK).bodyValue(otpDTO));
    }
}
