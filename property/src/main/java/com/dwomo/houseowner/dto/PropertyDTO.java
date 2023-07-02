package com.dwomo.houseowner.dto;

import com.dwomo.houseowner.aggregate.valueObject.LandArea;
import com.dwomo.houseowner.aggregate.valueObject.Message;
import com.dwomo.houseowner.aggregate.valueObject.Picture;
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
    private String landTitleNumber;
    private BigDecimal price;
    private LandArea landArea;
    private double numberOfPlots;
    private String landType;
    private String location;
    private List<Message> messages;
    private List<Picture> pictures;
    private String status;
    private boolean deleted;
    @CreatedBy
    private String createdBy;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
