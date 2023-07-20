package com.houseowner.edge.aggregates.entities;

import com.houseowner.edge.aggregates.valueobjects.Address;
import com.houseowner.edge.aggregates.valueobjects.Picture;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")
@Component
public class User {

    // Define the regular expressions
    private static final String FIRST_NAME_REG_EXP = "^[A-Za-z'-]{2,30}$";
    private static final String SURNAME_REG_EXP = "^[A-Za-z'-]{2,30}$";
    private static final String OTHER_NAME_REG_EXP = "^[A-Za-z'-]{2,30}$";
    private static final String NATIONAL_ID_NUMBER_REG_EXP = "^[A-Za-z0-9\\-]+$";
    private static final String PHONE_NUMBER_REG_EXP =  "^(\\+\\d{1,3}\\s?)?(\\(\\d{1,4}\\)|\\d{1,4})[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,9})$";
    private static final String EMAIL_REG_EXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    // Define the private fields of the User
    @Id
    private String id;
    @NotBlank(message = "The first name field cannot be empty.")
    @Pattern(regexp = FIRST_NAME_REG_EXP, message = "You must enter a valid first name.")
    private String firstName;
    @NotBlank(message = "The surname field cannot be empty.")
    @Pattern(regexp = SURNAME_REG_EXP, message = "You must enter a valid surname.")
    private String surname;
    @Pattern(regexp = OTHER_NAME_REG_EXP, message = "You must enter a valid other name.")
    private String otherName;
    private String fullName = getFirstName() + " " +  getOtherName() + " " + getSurname();
    @NotBlank(message = "The date field cannot be empty.")
    private String dateOfBirth;
    @NotBlank(message = "The national identification number cannot be empty.")
    @Pattern(regexp = NATIONAL_ID_NUMBER_REG_EXP, message = "You must enter a valid national identification number.")
    private String nationalIdNumber;
    @NotBlank(message = "The addresses field cannot be empty.")
    private List<Address> addresses;
    private List<Picture> idPicture;
    //@Pattern(regexp = PHONE_NUMBER_REG_EXP, message = "You must enter a valid phone number.")
    private String phoneNumber;
    @Pattern(regexp = EMAIL_REG_EXP, message = "You must enter a valid email.")
    private String email;
    private String status;
    private boolean deleted;
    @CreatedBy
    private String createdBy;
    private LocalDateTime createdAt = LocalDateTime.now();
    private List<String> roles;

    public User(User user) {

        super();
    }
}
