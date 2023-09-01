package com.houseowner.edge.services;


import com.houseowner.edge.configuration.TwilioConfig;
import com.houseowner.edge.dto.OTPDTO;
import com.houseowner.edge.dto.OTPResponse;
import com.houseowner.edge.dto.OTPStatus;
import com.houseowner.edge.events.publishers.EventPublisher;
import com.houseowner.edge.repositories.OTPCacheRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import java.text.DecimalFormat;
import java.util.Random;

@Service
public class OTPService {

    private final TwilioConfig twilioConfig;
    private final EventPublisher eventPublisher;
    private final OTPCacheRepository otpCacheRepository;
    private static final String INVALID_OTP_TAG = "INVALID";
    private static final String TOPIC_NAME = "otp-created";



    public OTPService(TwilioConfig twilioConfig, OTPCacheRepository otpCacheRepository, EventPublisher eventPublisher)
    {
        this.twilioConfig = twilioConfig;
        this.eventPublisher = eventPublisher;
        this.otpCacheRepository = otpCacheRepository;
    }

    public Mono<OTPResponse> sendOTP(OTPDTO otpdto) {

        OTPResponse OTPResponse;
        OTPDTO otpCreatedEventDTO = null;

        try {
            PhoneNumber to = new PhoneNumber(otpdto.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

            String otpString = generateOTP();

            otpCreatedEventDTO = new OTPDTO(otpString, otpdto.getPhoneNumber());
            otpCreatedEventDTO.setOtpValid("N/A");

            String otpMessage = "Hello dear, your OTP is: " + otpString + ". Kindly use this OTP to complete the Houseowner user registration. Thank you";

            // Send otp message to SMS Service
            Message.creator(to, from, otpMessage).create();

            OTPResponse = new OTPResponse(OTPStatus.DELIVERED, otpMessage);

        } catch (Exception exception) {
            OTPResponse = new OTPResponse(OTPStatus.FAILED, exception.getMessage());
        }

        // Send the OTP to the broker

        eventPublisher.publishToBroker(otpCreatedEventDTO, TOPIC_NAME);

        return Mono.just(OTPResponse);
    }


    private String generateOTP()
    {
        return new DecimalFormat("0000000")
                .format(new Random().nextInt(9999999));
    }



    public Mono<Boolean> validateOTP(OTPDTO otpdto)
    {
        return verifyOTP(otpdto);
    }



    private Mono<Boolean> verifyOTP(OTPDTO otpdto)
    {
        return otpCacheRepository.findByOtpString(otpdto.getPhoneNumber())
                .filter(otpCreatedEvent -> otpCreatedEvent.getOtpString().equals(otpdto.getPhoneNumber()) && otpCreatedEvent.getPhoneNumber().equals(otpdto.getOtpString()) && otpCreatedEvent.getOtpValid().equals("N/A"))
                .doOnNext(otpCreatedEvent -> otpCreatedEvent.setOtpValid(INVALID_OTP_TAG))
                .doOnNext(otpCacheRepository::save)
                .hasElement();
    }




    public Mono<ServerResponse> sendOTPServer(ServerRequest serverRequest)
    {

        return serverRequest.bodyToMono(OTPDTO.class)
                .flatMap(this::sendOTP)
                .flatMap(otpDTO -> ServerResponse.status(HttpStatus.OK).body(BodyInserters.fromValue(otpDTO)));
    }


    public Mono<ServerResponse> validateOTP(ServerRequest serverRequest)
    {
        return serverRequest.bodyToMono(OTPDTO.class)
                .flatMap(this::validateOTP)
                .flatMap(otpDTO -> ServerResponse.status(HttpStatus.OK).bodyValue(otpDTO));
    }
}
