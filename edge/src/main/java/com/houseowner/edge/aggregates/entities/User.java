package com.houseowner.edge.aggregates.entities;

import com.houseowner.edge.aggregates.valueObjects.Address;
import com.houseowner.edge.aggregates.valueObjects.Picture;
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
import java.util.Date;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "users")
@Component
public class User {

    @Id
    private String id;
    @NotBlank(message = "The first name field cannot be empty.")
    @Pattern(regexp = "^[A-Za-z'-]{2,30}$", message = "You must enter a valid first name.")
    private String firstName;
    @NotBlank(message = "The surname field cannot be empty.")
    @Pattern(regexp = "^[A-Za-z'-]{2,30}$", message = "You must enter a valid surname.")
    private String surname;
    @Pattern(regexp = "^[A-Za-z'-]{2,30}$", message = "You must enter a valid other name.")
    private String otherName;
    @NotBlank(message = "The date field cannot be empty.")
    private Date dateOfBirth;
    @NotBlank(message = "The national identification number cannot be empty.")
    @Pattern(regexp = "^[A-Za-z0-9\\-]+$", message = "You must enter a valid national identification number.")
    private String nationalIdNumber;
    @NotBlank(message = "The addresses field cannot be empty.")
    private List<Address> addresses;
    private List<Picture> idPicture;
    @Pattern(
            regexp = "^(\\+\\d{1,3}\\s?)?(\\(\\d{1,4}\\)|\\d{1,4})[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,9})$",
            message = "You must enter a valid phone number.")
    private String phoneNumber;
    @Pattern(
            regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$",
            message = "You must enter a valid email.")
    private String email;
    private String status;
    private boolean deleted;
    @CreatedBy
    private String createdBy;
    private LocalDateTime createdAt = LocalDateTime.now();
}
