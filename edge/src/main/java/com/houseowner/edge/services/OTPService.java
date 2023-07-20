package com.houseowner.edge.services;


import com.houseowner.edge.aggregates.entities.OneTimePassword;
import com.houseowner.edge.configuration.TwilioConfig;
import com.houseowner.edge.dto.OTPStatus;
import com.houseowner.edge.dto.OneTimePasswordDTO;
import com.houseowner.edge.dto.OneTimePasswordResponseDTO;
import com.houseowner.edge.repositories.OneTimePasswordRepository;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import java.text.DecimalFormat;
import java.util.Random;

@Service
public class OTPService {

    private final TwilioConfig twilioConfig;
    private final OneTimePasswordRepository oneTimePasswordRepository;



    public OTPService(TwilioConfig twilioConfig, OneTimePasswordRepository oneTimePasswordRepository)
    {
        this.twilioConfig = twilioConfig;
        this.oneTimePasswordRepository = oneTimePasswordRepository;
    }

    public Mono<OneTimePasswordResponseDTO> sendOTP(OneTimePasswordDTO oneTimePasswordDTO)
    {
        OneTimePasswordResponseDTO oneTimePasswordResponseDTO = null;

        try {
            PhoneNumber to = new PhoneNumber(oneTimePasswordDTO.getPhoneNumber());
            PhoneNumber from = new PhoneNumber(twilioConfig.getTrialNumber());
            OneTimePassword otp = generateOTP(oneTimePasswordDTO.getPhoneNumber());

            String otpString = otp.getOneTimePassword();
            String otpMessage = "Hello dear, your OTP is: " + otpString + ". Use this OTP to complete the user registration. Thank you";

            Message.creator(to, from, otpMessage).create();

            oneTimePasswordRepository.insert(otp);

            oneTimePasswordResponseDTO = new OneTimePasswordResponseDTO(OTPStatus.DELIVERED, otpMessage);

        } catch (Exception exception) {
            oneTimePasswordResponseDTO = new OneTimePasswordResponseDTO(OTPStatus.FAILED, exception.getMessage());
        }

        return Mono.just(oneTimePasswordResponseDTO);
    }


    private OneTimePassword generateOTP(String phoneNumberTo)
    {
        String otpString =  new DecimalFormat("0000000")
                .format(new Random().nextInt(9999999));

        return new OneTimePassword(otpString, phoneNumberTo);
    }



    public Mono<Boolean> validateOTP(OneTimePasswordDTO oneTimePassword)
    {
       Mono<OneTimePassword> oneTimePasswordFromDatabase = oneTimePasswordRepository.findOneTimePassword(oneTimePassword.getOneTimePassword());

       Mono<Boolean> result = oneTimePasswordFromDatabase.map(otp -> otp.getOneTimePassword().equals(oneTimePassword.getOneTimePassword()));
       oneTimePasswordRepository.deleteOneTimePassword(oneTimePassword.getOneTimePassword());
       return result;
    }

}
