package com.houseowner.edge.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class OneTimePasswordResponseDTO {

    private OTPStatus status;
    private String message;
}
