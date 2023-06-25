package com.houseowner.edge.aggregates.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class User {

    private String firstName;
    private String surname;
    private String otherName;
    private String phoneNumber;
    private String nationalIdNumber;
    private String gender;
    private String frontNationalId;
    private String backNationalId;
    private String nationality;
}