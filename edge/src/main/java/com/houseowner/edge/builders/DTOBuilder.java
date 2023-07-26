package com.houseowner.edge.builders;


import com.houseowner.edge.aggregates.valueobjects.Address;
import com.houseowner.edge.aggregates.valueobjects.Picture;
import com.houseowner.edge.dto.CredentialDTO;
import com.houseowner.edge.dto.DTO;
import com.houseowner.edge.dto.OTPStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class DTOBuilder {

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



    public DTOBuilder setCredentialType(String credentialType)
    {
        this.type = credentialType;
        return this;
    }



    public DTOBuilder setCredentialValue(String credentialValue)
    {
        this.value = credentialValue;
        return this;
    }



    public DTOBuilder setCredentialStatus(boolean credentialTemporaryStatus)
    {
        this.temporary = credentialTemporaryStatus;
        return this;
    }



    public DTOBuilder setUsername(String username)
    {
        this.username = username;
        return this;
    }



    public DTOBuilder setPassword(String password)
    {
        this.password = password;
        return this;
    }




    public DTOBuilder setOTPString(String otpString)
    {
        this.otpString = otpString;
        return this;
    }




    public DTOBuilder setOTPStatus(OTPStatus otpStatus)
    {
        this.otpStatus = otpStatus;
        return this;
    }




    public DTOBuilder setTopic(String topic)
    {
        this.topic = topic;
        return this;
    }



    public DTOBuilder setSubscription(String subscription)
    {
        this.subscription = subscription;
        return this;
    }





    public DTOBuilder setCredentialEnabledStatus(boolean credentialEnabledStatus)
    {
        this.enabled = credentialEnabledStatus;
        return this;
    }




    public DTOBuilder setCredential(List<CredentialDTO> credentials)
    {
        this.credentials = credentials;
        return this;
    }





    public DTOBuilder setRealmRoles(List<String> realmRoles)
    {
        this.realmRoles = realmRoles;
        return this;
    }




    public DTOBuilder setFirstName(String firstName)
    {
        this.firstName = firstName;
        return this;
    }





    public DTOBuilder setSurname(String surname)
    {
        this.surname = surname;
        return this;
    }




    public DTOBuilder setOtherName(String otherName)
    {
        this.otherName = otherName;
        return this;
    }




    public DTOBuilder setDateOfBirth(String dateOfBirth)
    {
        this.dateOfBirth = dateOfBirth;
        return this;
    }




    public DTOBuilder setNationalIdNumber(String nationalIdNumber)
    {
        this.nationalIdNumber = nationalIdNumber;
        return this;
    }





    public DTOBuilder setAddress(List<Address> addresses)
    {
        this.addresses = addresses;
        return this;
    }





    public DTOBuilder setIdPicture(List<Picture> idPicture)
    {
        this.idPicture = idPicture;
        return this;
    }




    public DTOBuilder setPhoneNumber(String phoneNumber)
    {
        this.phoneNumber = phoneNumber;
        return this;
    }




    public DTOBuilder setEmail(String email)
    {
        this.email = email;
        return this;
    }





    public DTOBuilder setStatus(String status)
    {
        this.status = status;
        return this;
    }




    public DTOBuilder setDeleted(boolean deleted)
    {
        this.deleted = deleted;
        return this;
    }


    public DTO build() {

        DTO dto = DTO.getInstance();
        dto.setId(this.id);
        dto.setAddresses(this.addresses);
        dto.setAccess_token(this.access_token);
        dto.setCredentials(this.credentials);
        dto.setEnabled(this.enabled);
        dto.setUsername(this.username);
        dto.setFirstName(this.firstName);
        dto.setSurname(this.surname);
        dto.setEmail(this.email);
        dto.setIdPicture(this.idPicture);
        dto.setMessage(this.message);
        dto.setStatus(this.status);
        dto.setDeleted(this.deleted);
        dto.setOtherName(this.otherName);
        dto.setDateOfBirth(this.dateOfBirth);
        dto.setExpires_in(this.expires_in);
        dto.setNot_before_policy(this.not_before_policy);
        dto.setNationalIdNumber(this.nationalIdNumber);
        dto.setOtpString(this.otpString);
        dto.setOtpStatus(this.otpStatus);

        return dto;
    }

}
