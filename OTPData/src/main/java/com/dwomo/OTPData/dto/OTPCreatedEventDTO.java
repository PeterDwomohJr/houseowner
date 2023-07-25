package com.dwomo.OTPData.dto;


import lombok.Data;
import org.springframework.stereotype.Component;


@Data
@Component
public class OTPCreatedEventDTO {

    private String id;
    private String phoneNumber;
    private String otpString;
}
