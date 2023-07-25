package com.houseowner.edge.services;


import com.houseowner.edge.configuration.TwilioConfig;
import com.houseowner.edge.dto.OTPStatus;
import com.houseowner.edge.dto.OTPDTO;
import com.houseowner.edge.dto.OneTimePasswordResponseDTO;
import com.houseowner.edge.events.Domain.OTPCreatedEvent;
import com.houseowner.edge.events.publishers.OTPEventPublisher;
import com.houseowner.edge.repositories.OTPCacheRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.text.DecimalFormat;
import java.util.Random;

@Service
public class OTPService {

    private final TwilioConfig twilioConfig;
    private final OTPEventPublisher otpEventPublisher;
    private final OTPCacheRepository otpCacheRepository;



    public OTPService(TwilioConfig twilioConfig, OTPCacheRepository otpCacheRepository, OTPEventPublisher otpEventPublisher)
    {
        this.twilioConfig = twilioConfig;
        this.otpEventPublisher = otpEventPublisher;
        this.otpCacheRepository = otpCacheRepository;
    }

    public Mono<OneTimePasswordResponseDTO> sendOTP(OTPDTO OTPDTO) {

        OneTimePasswordResponseDTO oneTimePasswordResponseDTO = null;
        OTPCreatedEvent otpCreatedEvent = new OTPCreatedEvent();

        try {
            PhoneNumber to = new PhoneNumber(OTPDTO.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

            String otpString = generateOTP();

            otpCreatedEvent.setOtpString(otpString);
            otpCreatedEvent.setPhoneNumber(OTPDTO.getPhoneNumber());

            String otpMessage = "Hello dear, your OTP is: " + otpString + ". Use this OTP to complete the Houseowner user registration. Thank you";

            Message.creator(to, from, otpMessage).create();

            oneTimePasswordResponseDTO = new OneTimePasswordResponseDTO(OTPStatus.DELIVERED, otpMessage);

        } catch (Exception exception) {
            oneTimePasswordResponseDTO = new OneTimePasswordResponseDTO(OTPStatus.FAILED, exception.getMessage());
        }

        otpEventPublisher.publishOTPToBroker(otpCreatedEvent);

        return Mono.just(oneTimePasswordResponseDTO);
    }


    private String generateOTP()
    {
        return new DecimalFormat("0000000")
                .format(new Random().nextInt(9999999));
    }




    public Mono<Boolean> validateOTP(String otpString, String phoneNumber)
    {
        deleteOtpAfterValidation(phoneNumber).subscribe();

        Mono<Boolean> result = verifyOTP(otpString, phoneNumber);

        return result;

    }



    private Mono<Boolean> verifyOTP(String otpString, String phoneNumber)
    {
        return otpCacheRepository.findByOtpString(phoneNumber)
                .filter(otpToken -> otpToken.getOtpString().equals(phoneNumber) && otpToken.getPhoneNumber().equals(otpString))
                .hasElement();
    }

    private Mono<Void> deleteOtpAfterValidation(String phoneNumber)
    {
       return  otpCacheRepository.deleteByPhoneNumber(phoneNumber);
    }

}
