package com.houseowner.property.services;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.adapters.databaseAdapter.repository.PropertyRepository;
import com.houseowner.property.aggregates.entities.Property;
import com.houseowner.property.utilities.AppUtils;
import org.springframework.data.domain.Range;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PropertyService {
    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository)
    {
        this.propertyRepository = propertyRepository;
    }

    //@PreAuthorize("hasRole('AUTHENTICATED_USER')")
    public void createProperty(PropertyDTO propertyDTO) {

        Property property = new Property();

        Mono<Property> propertyMono = property.create(propertyDTO);

        propertyMono.flatMap(propertyRepository::save);
    }


    //@PostFilter("filterObject.owner == authentication.name")
    public Mono<PropertyDTO> getProperty(String id) {

        // return the property with an id derived from the property
        return propertyRepository.findById(id).map(AppUtils::entityToDTO);
    }


    public Flux<PropertyDTO> listProperties() {

        // return all the properties that is in the repository
        return propertyRepository.findAll().map(AppUtils::entityToDTO);
    }


    public Flux<PropertyDTO> listPropertiesInPriceRange(double min, double max) {

        return propertyRepository.findByPriceBetween(Range.closed(min, max));
    }


    public Mono<PropertyDTO> saveProperty(Mono<PropertyDTO> propertyDTO) {

        return propertyDTO.map(AppUtils::dtoToEntity)
                .flatMap(propertyRepository::insert)
                .map(AppUtils::entityToDTO);
    }


    //@PreAuthorize("hasRole('AUTHENTICATED_USER')")
    public Mono<PropertyDTO> updateProperty(String id, Mono<PropertyDTO> propertyDTO) {

        return propertyRepository.findById(id)
                .flatMap(property -> propertyDTO.map(AppUtils::dtoToEntity))
                .doOnNext(productEntity -> productEntity.setId(id))
                .flatMap(propertyRepository::save)
                .map(AppUtils::entityToDTO);
    }

    /**
     * The default deletion is soft-deletion
     */
    //@PreAuthorize("hasRole('AUTHENTICATED_USER')")
    public Mono<Void> deleteProperty(String id) {

        return propertyRepository.deleteById(id);
    }


    //@PreAuthorize("hasRole('AUTHENTICATED_USER')")
    public void buyProperty(String id) {

        Mono<PropertyDTO> propertyMono = this.getProperty(id)
                .doOnNext(propertyEntity -> propertyEntity.setDeleted(true));

    }


    //@PreAuthorize("hasRole('ADMIN')")
    public void changePropertyStatus(String id) {

        Mono<PropertyDTO> propertyMono = this.getProperty(id)
                .doOnNext(propertyEntity -> propertyEntity.setStatus("ACTIVE"));
    }

}
