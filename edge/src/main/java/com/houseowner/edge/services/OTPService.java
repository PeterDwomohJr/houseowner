package com.houseowner.edge.services;


import com.houseowner.edge.builders.DTOBuilder;
import com.houseowner.edge.configuration.TwilioConfig;
import com.houseowner.edge.dto.DTO;
import com.houseowner.edge.dto.OTPDTO;
import com.houseowner.edge.dto.OTPResponseDTO;
import com.houseowner.edge.dto.OTPStatus;
import com.houseowner.edge.events.publishers.EventPublisher;
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
    private final EventPublisher eventPublisher;
    private final OTPCacheRepository otpCacheRepository;
    private static final String INVALID_OTP_TAG = "INVALID";



    public OTPService(TwilioConfig twilioConfig, OTPCacheRepository otpCacheRepository, EventPublisher eventPublisher)
    {
        this.twilioConfig = twilioConfig;
        this.eventPublisher = eventPublisher;
        this.otpCacheRepository = otpCacheRepository;
    }

    public Mono<OTPResponseDTO> sendOTP(OTPDTO OTPDTO) {

        OTPResponseDTO OTPResponseDTO;
        DTO otpCreatedEventDTO = null;

        try {
            PhoneNumber to = new PhoneNumber(OTPDTO.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());

            String otpString = generateOTP();

            otpCreatedEventDTO = new DTOBuilder()
                    .setOTPString(otpString)
                    .setPhoneNumber(OTPDTO.getPhoneNumber())
                    .build();

            String otpMessage = "Hello dear, your OTP is: " + otpString + ". Kindly se this OTP to complete the Houseowner user registration. Thank you";

            // Send otp message to SMS Se
            Message.creator(to, from, otpMessage).create();

            OTPResponseDTO = new OTPResponseDTO(OTPStatus.DELIVERED, otpMessage);

        } catch (Exception exception) {
            OTPResponseDTO = new OTPResponseDTO(OTPStatus.FAILED, exception.getMessage());
        }

        // Send the OTP to the broker

        eventPublisher.publishToBroker(otpCreatedEventDTO, "otp-created-topic");

        return Mono.just(OTPResponseDTO);
    }


    private String generateOTP()
    {
        return new DecimalFormat("0000000")
                .format(new Random().nextInt(9999999));
    }



    public Mono<Boolean> validateOTP(String otpString, String phoneNumber)
    {
        return verifyOTP(otpString, phoneNumber);
    }



    private Mono<Boolean> verifyOTP(String otpString, String phoneNumber)
    {
        return otpCacheRepository.findByOtpString(phoneNumber)
                .filter(otpToken -> otpToken.getOtpString().equals(phoneNumber) && otpToken.getPhoneNumber().equals(otpString) && otpToken.getOtpValid().equals("N/A"))
                .doOnNext(otpCreatedEventDTO -> otpCreatedEventDTO.setOtpValid(INVALID_OTP_TAG))
                .hasElement();
    }
}
