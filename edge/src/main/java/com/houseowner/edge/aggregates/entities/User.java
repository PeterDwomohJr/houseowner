package com.houseowner.edge.aggregates.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "users")
@Component
public class User {

    @Id
    private String id;
    private String firstName;
    private String surname;
    private String otherName;
    private Date dateOfBirth;
    private String nationalIdNumber;
    private
}
