package com.houseowner.edge.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class RegisterUserDTO {

    private String username;
    private String firstName;
    private String lastName;
    private boolean enabled;
    private List<CredentialDTO> credentials;
    private List<String> realmRoles;
}
