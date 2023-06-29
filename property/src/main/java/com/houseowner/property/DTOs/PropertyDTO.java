package com.houseowner.property.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PropertyDTO {

    private String id;
    private String type;
    private String landTitleNumber;
    private double landSize;
    private BigDecimal price;
    private String location;
    private String pictures;
    private String messages;
    private String status;
    private String owner;
    private boolean deleted;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    @CreatedBy
    private String createdBy;
    private final double DEFAULT_DOUBLE_VALUE = 0.0;
    private final String DEFAULT_PROPERTY_STATUS = "PENDING";
    private final String ACTIVE_PROPERTY_VALUE = "ACTIVE";

}
