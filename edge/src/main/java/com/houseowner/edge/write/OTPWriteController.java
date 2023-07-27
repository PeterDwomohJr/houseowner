package com.houseowner.edge.write;


import com.houseowner.edge.services.OTPService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class OTPWriteController {

    private final OTPService otpService;



    public OTPWriteController(OTPService otpService)
    {
        this.otpService = otpService;
    }


    @Bean
    public RouterFunction<ServerResponse> sendOTP()
    {
        return RouterFunctions.route()
                .POST("api/v0/otp/send", otpService::sendOTP)
                .POST("api/v0/otp/validate", otpService::validateOTP)
                .build();
    }
}
