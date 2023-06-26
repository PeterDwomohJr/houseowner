package com.houseowner.property.interfaces;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.aggregates.entities.Property;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.List;

/**
 * @author Peter Dwomoh Junior
 * @version 0.0.0
 * @since 0.0.0
 *
 * Invariant: A property will have messages that defines the communication that has taken place between
 * the buyer and seller. No personal or contact information must be shared between the buyer and the seller.
 */

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
    List<String> getPictures();
    String getStatus();
    String getOwner();
    boolean isDeleted();
}
