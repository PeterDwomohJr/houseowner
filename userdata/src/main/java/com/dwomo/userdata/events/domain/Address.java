package com.dwomo.userdata.events.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Address {

    private String continent;
    private String country;
    private String state;
    private String city;
    private String community;
    private String street;
    private String houseNumber;
    private String postCode;
}
