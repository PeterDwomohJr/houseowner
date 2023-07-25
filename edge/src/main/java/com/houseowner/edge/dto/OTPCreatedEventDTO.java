package com.houseowner.edge.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OTPCreatedEventDTO {

    private String id;
    private String otpString;
    private String phoneNumber;

}
