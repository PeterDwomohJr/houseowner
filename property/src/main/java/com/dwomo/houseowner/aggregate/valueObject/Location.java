package com.dwomo.houseowner.aggregate.valueObject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Component
public class Location {

    private String name;
    private String longitude;
    private String latitude;
}
