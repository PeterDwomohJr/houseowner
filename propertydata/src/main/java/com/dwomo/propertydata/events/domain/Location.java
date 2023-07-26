package com.dwomo.propertydata.events.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Location {

    private String name;
    private String longitude;
    private String latitude;
}
