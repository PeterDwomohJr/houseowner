package com.houseowner.edge.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OTPDTO {

    private String id;
    private String otpString;
    private String phoneNumber;
    private String otpValid = "N/A";

    public OTPDTO(String otpString, String phoneNumber) {
        this.otpString = otpString;
        this.phoneNumber = phoneNumber;
    }
}


