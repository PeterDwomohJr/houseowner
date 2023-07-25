package com.houseowner.edge.write;


import com.houseowner.edge.services.TwilioOTPService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class OTPWriteController {

    private final TwilioOTPService otpHandler;



    public OTPWriteController(TwilioOTPService otpHandler)
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
