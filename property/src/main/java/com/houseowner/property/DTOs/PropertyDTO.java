package com.houseowner.property.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@Component
public class PropertyDTO {

    private String type;
    private String landTitleNumber;
    private double landSize;
    private BigDecimal price;
    private String location;
    private String pictures;
    private String owner;
}
