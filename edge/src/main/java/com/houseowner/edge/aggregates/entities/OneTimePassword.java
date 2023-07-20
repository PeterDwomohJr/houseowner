package com.houseowner.edge.aggregates.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@Document("one_time_passwords")
@Component
public class OneTimePassword {

    @Id
    private String id;
    private String oneTimePassword;
    private String findOneTimePassword;
    private String deleteOneTimePassword;
    private String phoneNumber;


    public OneTimePassword(String oneTimePassword)
    {
        this.oneTimePassword = oneTimePassword;
    }


    public OneTimePassword(String oneTimePassword, String phoneNumber)
    {
        this.oneTimePassword = oneTimePassword;
        this.phoneNumber = phoneNumber;
    }


    public OneTimePassword(String id, String oneTimePassword, String phoneNumber)
    {
        this.id = id;
        this.oneTimePassword = oneTimePassword;
        this.phoneNumber = phoneNumber;
    }
}
