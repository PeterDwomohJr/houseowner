package com.houseowner.edge.aggregates.valueobjects;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
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
