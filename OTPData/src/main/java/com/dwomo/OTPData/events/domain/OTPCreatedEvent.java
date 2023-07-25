package com.dwomo.OTPData.events.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "otps")
@Component
public class OTPCreatedEvent {

    @Id
    private String id;
    private String phoneNumber;
    private String otpString;
}
