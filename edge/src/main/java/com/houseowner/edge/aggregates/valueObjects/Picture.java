package com.houseowner.edge.aggregates.valueObjects;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Picture {

    private String frontPictureURL;
    private String backPictureURI;
    private String metadata;
}
