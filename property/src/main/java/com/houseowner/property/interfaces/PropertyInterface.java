package com.houseowner.property.interfaces;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.aggregates.entities.Property;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

public interface PropertyInterface {

    Mono<Property> create(PropertyDTO propertyDTO);
    void update(PropertyDTO propertyDTO);
    void delete();
    void buy();
    void changeStatus();
    void setId(String Id);
    void setOwner(String owner);
    String getType();
    String getLandTitleNumber();
    double getLandSize();
    BigDecimal getPrice();
    String getLocation();
    String getPictures();
    String getStatus();
    String getOwner();
    boolean isDeleted();
}
