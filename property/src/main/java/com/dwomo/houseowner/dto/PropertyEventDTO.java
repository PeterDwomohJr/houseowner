package com.dwomo.houseowner.dto;


import com.dwomo.houseowner.events.domain.LandArea;
import com.dwomo.houseowner.events.domain.Location;
import com.dwomo.houseowner.events.domain.Message;
import com.dwomo.houseowner.events.domain.Picture;
import com.dwomo.houseowner.utils.NoPersonallyIdentifiableInformation;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.stereotype.Component;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class PropertyEventDTO {

    private String id;
    @NotBlank(message = "The land title number field cannot be empty.")
    @Pattern(regexp = "^[0-9]{10}$", message = "You must enter a valid land title number.")
    private String landTitleNumber;
    @NotNull(message = "The price field cannot be empty.")
    @DecimalMin("1000.00")
    @DecimalMax("3000000000")
    private BigDecimal price;
    @NotNull(message = "The land area field cannot be empty.")
    private LandArea landArea;
    @NotNull(message = "The number of plot field cannot be empty")
    @DecimalMin("0.1")
    @DecimalMax("1000000.0")
    private Double numberOfPlots;
    @NotBlank(message = "The land type field cannot be empty")
    private String landType;
    @NotBlank(message = "The property type field cannot be empty")
    private String propertyType;
    @NotNull(message = "The location field cannot be empty")
    private Location location;
    @NoPersonallyIdentifiableInformation()
    private List<Message> messages;
    private List<Picture> pictures;
    @Pattern(regexp = "^(PENDING|ACTIVE)$", message = "You must enter a valid status")
    private String status;
    private boolean deleted;
    @CreatedBy
    private String createdBy;
    private Date createdAt = new Date();
}
