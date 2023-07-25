package com.houseowner.edge.events.Domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "local-otps")
@Component
public class OTPCreatedEvent {

    @Id
    private String id;
    private String otpString;
    private String phoneNumber;
}
