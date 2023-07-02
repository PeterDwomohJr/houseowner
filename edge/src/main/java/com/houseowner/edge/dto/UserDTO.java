package com.houseowner.edge.dto;


import com.houseowner.edge.aggregates.valueObjects.Address;
import com.houseowner.edge.aggregates.valueObjects.Picture;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class UserDTO {

    private String id;
    private String firstName;
    private String surname;
    private String otherName;
    private Date dateOfBirth;
    private String nationalIdNumber;
    private List<Address> addresses;
    private List<Picture> idPicture;
    private String phoneNumber;
    private String email;
    private String status;
    private boolean deleted;
    @CreatedBy
    private String createdBy;
    private LocalDateTime createdAt = LocalDateTime.now();
}
