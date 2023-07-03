package com.dwomo.houseowner.aggregate.entities;

import com.dwomo.houseowner.aggregate.valueObject.LandArea;
import com.dwomo.houseowner.aggregate.valueObject.Location;
import com.dwomo.houseowner.aggregate.valueObject.Message;
import com.dwomo.houseowner.aggregate.valueObject.Picture;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "properties")
@Component
public class Property {

    @Id
    private String id;
    @NotBlank(message = "The land title number field cannot be empty.")
    @Pattern(regexp = "^[0-9]{10}$", message = "You must enter a valid land title number.")
    private String landTitleNumber;
    //@NotNull(message = "The price field cannot be empty.")
    //@Pattern(regexp = "^\\$?\\d+(?:,?\\d{3})*(?:\\.\\d{2})?$", message = "You must enter a valid price.")
    private BigDecimal price;
    //@NotNull(message = "The land area field cannot be empty.")
    private LandArea landArea;
    //@NotNull(message = "The number of plot field cannot be empty.")
    //@Pattern(regexp = "^\\d+(\\.\\d+)?$", message = "You must enter valid number of plots.")
    private double numberOfPlots;
    @NotBlank(message = "The land type field cannot be empty")
    private String landType;
    @NotBlank(message = "The property type cannot be empty")
    private String propertyType;
    //@NotNull(message = "The location field cannot be empty")
    private Location location;
    //@Pattern(
            //regexp = "^(?!.*(?:\\b\\d{10}\\b|(?i)at\\b|@[a-zA-Z0-9._-]+\\.[a-zA-Z]{2,4}\\b|\\b(?:facebook|twitter|instagram)\\.com\\b)).*$",
            //message = "You cannot share any personal communication information.")
    private List<Message> messages;
    private List<Picture> pictures;
    @Pattern(regexp = "^(PENDING|ACTIVE)$", message = "You must enter a valid status")
    private String status;
    private boolean deleted;
    @CreatedBy
    private String createdAt;
    private LocalDateTime dateCreated = LocalDateTime.now();
}
