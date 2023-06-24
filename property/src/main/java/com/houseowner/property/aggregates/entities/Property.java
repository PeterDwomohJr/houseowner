package com.houseowner.property.aggregates.entities;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.interfaces.PropertyInterface;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Document("property")
@Component
public class Property implements PropertyInterface {

    @Id
    private String id;
    private String type;
    private String landTitleNumber;
    private double landSize;
    private BigDecimal price;
    private String location;
    private String pictures;
    private String status;
    private String owner;
    private boolean deleted;
    private LocalDateTime dateCreated;
    private LocalDateTime dateModified;
    private final double DEFAULT_DOUBLE_VALUE = 0.0;
    private final String DEFAULT_PROPERTY_STATUS = "PENDING";
    private final String ACTIVE_PROPERTY_VALUE = "ACTIVE";


    @Override
    public Mono<Property> create(PropertyDTO propertyDTO) {

        Property property = new Property();

        property.setType(propertyDTO.getType());
        property.setLandTitleNumber(propertyDTO.getLandTitleNumber());
        property.setLandSize(propertyDTO.getLandSize());
        property.setPrice(propertyDTO.getPrice());
        property.setLocation(property.getLocation());
        property.setPictures(propertyDTO.getPictures());
        property.setOwner(propertyDTO.getOwner());
        property.setDateCreated(LocalDateTime.now());
        property.setStatus(DEFAULT_PROPERTY_STATUS);
        property.setId(UUID.randomUUID().toString());

        return Mono.just(property);
    }


    @Override
    public void update(PropertyDTO propertyDTO) {

            if (!propertyDTO.getType().equals(null)) this.type = propertyDTO.getType();
            if (!propertyDTO.getLandTitleNumber().equals(null)) this.landTitleNumber = propertyDTO.getLandTitleNumber();
            if (propertyDTO.getLandSize() != DEFAULT_DOUBLE_VALUE) this.landSize = propertyDTO.getLandSize();
            if (!propertyDTO.getPrice().equals(null)) this.price = propertyDTO.getPrice();
            if (!propertyDTO.getLocation().equals(null)) this.location = propertyDTO.getLocation();
            if (!propertyDTO.getPictures().equals(null)) this.pictures = propertyDTO.getPictures();
            if (!propertyDTO.getOwner().equals(null)) this.owner = propertyDTO.getOwner();
            this.dateModified = LocalDateTime.now();
    }


    /**
     * The default deletion is soft-deletion.
     */
    @Override
    public void delete() {

        this.deleted = true;
    }


    /**
     * When a property is bought, it is soft-deleted
     */
    @Override
    public void buy() {
        if (this.status.equals(ACTIVE_PROPERTY_VALUE)) {
            this.delete();
        }
    }


    @Override
    public void changeStatus() {

        this.status = ACTIVE_PROPERTY_VALUE;
    }


    @Override
    public void setId(String id) {
        this.id = id;
    }
}

