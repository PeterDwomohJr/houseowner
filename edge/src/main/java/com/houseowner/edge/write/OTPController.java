package com.houseowner.edge.write;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class OTPController {

    private final TwilioOTPHandler otpHandler;



    public OTPController(TwilioOTPHandler otpHandler)
    {
        this.otpHandler = otpHandler;
    }


    @Bean
    public RouterFunction<ServerResponse> sendOTP()
    {
        return RouterFunctions.route()
                .POST("api/v0/otp/send", otpHandler::sendOTP)
                .POST("api/v0/otp/validate", otpHandler::validateOTP)
                .build();
    }

}
