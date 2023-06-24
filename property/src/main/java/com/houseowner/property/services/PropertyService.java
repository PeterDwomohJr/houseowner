package com.houseowner.property.services;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.adapters.databaseAdapter.repository.PropertyRepository;
import com.houseowner.property.aggregates.entities.Property;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    //@PreAuthorize("hasRole('AUTHENTICATED_USER')")
    public void createProperty(PropertyDTO propertyDTO) {

        Property property = new Property();

        Mono<Property> propertyMono = property.create(propertyDTO);

        propertyMono.flatMap(propertyRepository::save);
    }


    //@PostFilter("filterObject.owner == authentication.name")
    public Mono<Property> getProperty(String id) {

        // return the property with an id derived from the property
        return propertyRepository.findById(id);
    }


    public Flux<Property> listProperties() {

        // return all the properties that is in the repository
        return propertyRepository.findAll();
    }


    //@PreAuthorize("hasRole('AUTHENTICATED_USER')")
    public void updateProperty(String id, PropertyDTO propertyDTO) {

        Mono<Property> propertyMono = this.getProperty(id);

        propertyMono.flatMap(updatedProperty -> {

            updatedProperty.update(propertyDTO);

            return propertyMono;
        });
    }


    @PreAuthorize("hasRole('AUTHENTICATED_USER')")
    public void deleteProperty(String id) {

        Mono<Property> propertyMono = this.getProperty(id);

        propertyMono.flatMap(deletedProperty -> {

            deletedProperty.delete();

            return propertyMono;
        });
    }


    @PreAuthorize("hasRole('AUTHENTICATED_USER')")
    public void buyProperty(String id) {

        Mono<Property> propertyMono = this.getProperty(id);

        //TODO Implement the messaging logic between the buyer and the seller

        propertyMono.flatMap(boughtProperty -> {

            boughtProperty.buy();

            return propertyMono;
        });

    }


    @PreAuthorize("hasRole('ADMIN')")
    public void changePropertyStatus(String id) {

        Mono<Property> propertyMono = this.getProperty(id);

        propertyMono.flatMap(statusChangedProperty -> {

            statusChangedProperty.changeStatus();

            return propertyMono;
        });
    }
}
