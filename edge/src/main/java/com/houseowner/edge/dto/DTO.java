/**

package com.houseowner.edge.dto;


import com.houseowner.edge.aggregates.valueobjects.Address;
import com.houseowner.edge.aggregates.valueobjects.Picture;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.CreatedBy;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class DTO {

    // Define the regular expressions
    private static final String FIRST_NAME_REG_EXP = "^[A-Za-z'-]{2,30}$";
    private static final String SURNAME_REG_EXP = "^[A-Za-z'-]{2,30}$";
    private static final String NATIONAL_ID_NUMBER_REG_EXP = "^[A-Za-z0-9\\-]+$";
    private static final String PHONE_NUMBER_REG_EXP =  "^(\\+\\d{1,3}\\s?)?(\\(\\d{1,4}\\)|\\d{1,4})[-.\\s]?(\\d{1,4})[-.\\s]?(\\d{1,9})$";
    private static final String EMAIL_REG_EXP = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";


    private String type;
    private String value;
    private boolean temporary;
    private String username;
    private String password;
    private String access_token;
    private int expires_in;
    private int refresh_expires_in;
    private String refresh_token;
    private String token_type;
    private int not_before_policy;
    private String session_state;
    private String scope;
    private String id;
    private String otpString;
    private OTPStatus otpStatus;
    private String message;
    private String topic;
    private String subscription;
    private boolean enabled;
    private String otpValid = "N/A";
    private List<CredentialDTO> credentials;
    private List<String> realmRoles;
    //@NotBlank(message = "The first name field cannot be empty.")
    //@Pattern(regexp = FIRST_NAME_REG_EXP, message = "You must enter a valid first name.")
    private String firstName;
    //@NotBlank(message = "The surname field cannot be empty.")
    //@Pattern(regexp = SURNAME_REG_EXP, message = "You must enter a valid surname.")
    private String surname;
    private String otherName;
    //@NotBlank(message = "The date field cannot be empty.")
    private String dateOfBirth;
    //@NotBlank(message = "The national identification number cannot be empty.")
    //@Pattern(regexp = NATIONAL_ID_NUMBER_REG_EXP, message = "You must enter a valid national identification number.")
    private String nationalIdNumber;
    //@NotNull(message = "The addresses field cannot be empty.")
    private List<Address> addresses;
    private List<Picture> idPicture;
    //@NotBlank(message = "The phone number field cannot be empty")
    //@Pattern(regexp =PHONE_NUMBER_REG_EXP, message = "You must enter a valid phone number.")
    private String phoneNumber;
    //@Pattern(regexp = EMAIL_REG_EXP, message = "You must enter a valid email.")
    private String email;
    private String status;
    private boolean deleted;
    @CreatedBy
    private String createdBy;
    private LocalDateTime createdAt = LocalDateTime.now();
    private static DTO instance;



    // I use a private constructor to prevent a direct (automatic) instantiation
    private DTO() {}



    public static DTO getInstance()
    {
        if (instance == null) {
            return new DTO();
        }
        return instance;
    }
}

 **/
