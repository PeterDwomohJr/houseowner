package com.houseowner.edge.aggregates.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Document(collection = "tokens")
@Component
public class RefreshToken {

    @Id
    private String tokenId;
    private String token;
    private Instant expiryDate;
    private User user;
}
