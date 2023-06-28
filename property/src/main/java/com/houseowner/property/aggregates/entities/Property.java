package com.houseowner.property.aggregates.entities;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.interfaces.PropertyInterface;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author Peter Dwomoh Junior
 * @version 0.0.0
 * @since 0.0.0
 *
 * Class Invariant: A property will have messages that defines the communication that has taken place between
 * the buyer and seller. No personal or contact information must be shared between the buyer and the seller.
 */
@Data
@Document(collection = "property")
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
    private String messages;
    private String status;
    private String owner;
    private boolean deleted;
    //private LocalDateTime dateCreated;
    //private LocalDateTime dateModified;
    //private final double DEFAULT_DOUBLE_VALUE = 0.0;
    //private final String DEFAULT_PROPERTY_STATUS = "PENDING";
    //private final String ACTIVE_PROPERTY_VALUE = "ACTIVE";


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
        //property.setDateCreated(LocalDateTime.now());
        //property.setStatus(DEFAULT_PROPERTY_STATUS);
        //property.setId(UUID.randomUUID().toString());

        return Mono.just(property);
    }



    /**
     * When a property is bought, it is soft-deleted
     */
    @Override
    public void buy() {
        if (this.status.equals("ACTIVE")) { //ACTIVE_PROPERTY_VALUE)) {
            this.deleted = true;
        }
    }


    /**
     * This can be called only by only a person with an administrative account
     */

    @Override
    public void changeStatus() {

        this.status = "ACTIVE"; //ACTIVE_PROPERTY_VALUE;
    }


    @Override
    public void setId(String id) {
        this.id = id;
    }
}

