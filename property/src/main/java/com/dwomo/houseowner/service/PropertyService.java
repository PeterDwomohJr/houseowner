package com.dwomo.houseowner.service;

import com.dwomo.houseowner.aggregate.valueObject.Message;
import com.dwomo.houseowner.dto.PropertyDTO;
import com.dwomo.houseowner.repository.PropertyRepository;
import com.dwomo.houseowner.utils.PropertyUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;
import java.util.List;


/**
 * This represents the property service that implements the functionalities of a property (land and house)
 * Date: 1st July 2023
 *
 * @author Peter Dwomo Jr.
 * @version 0.0.0
 * @since 0.0.0
 */
@Service
public class PropertyService {

    private final String ACTIVE_STATUS = "ACTIVE";
    private final String PENDING_STATUS = "PENDING";
    private final PropertyRepository propertyRepository;


    /**
     *
     */
    public PropertyService(PropertyRepository propertyRepository)
    {   // perform constructor injection
        this.propertyRepository = propertyRepository;
    }


    public Flux<PropertyDTO> getProperties()
    {
        // returns all the properties in the property collection
        return propertyRepository.findAll().map(PropertyUtils::entityToDto);
    }


    public Mono<PropertyDTO> getProperty(String id)
    {
        return propertyRepository.findById(id).map(PropertyUtils::entityToDto);
    }


    public Mono<Long> getPropertyCount()
    {
        return propertyRepository.count();
    }


    public Flux<PropertyDTO> getActiveProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> property.getStatus().equals(ACTIVE_STATUS));
    }



    public Flux<PropertyDTO> getPendingProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> property.getStatus().equals(PENDING_STATUS));
    }



    public Flux<PropertyDTO> getSoftDeletedProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(PropertyDTO::isDeleted);
    }



    public Flux<PropertyDTO> getNonSoftDeletedProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> !property.isDeleted());
    }


    public Flux<PropertyDTO> getPropertiesBetweenPriceRange(BigDecimal min, BigDecimal max)
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
        .filter(property -> property.getPrice().compareTo(min) >= 0 && property.getPrice().compareTo(max) <= 0);
    }



    public Mono<PropertyDTO> saveProperty(Mono<PropertyDTO> propertyDTOMono)
    {
        return propertyDTOMono.map(PropertyUtils::dtoToEntity)
                .flatMap(propertyRepository::insert)
                .map(PropertyUtils::entityToDto);
    }


    public Mono<PropertyDTO> updateProperty(String id, Mono<PropertyDTO> propertyDTOMono)
    {
        return propertyRepository.findById(id)
                .flatMap(property -> propertyDTOMono.map(PropertyUtils::dtoToEntity))
                .doOnNext(propertyEntity -> propertyEntity.setId(id))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }


    public Mono<Void> deleteProperty(String id)
    {
        return propertyRepository.deleteById(id);
    }



    public Mono<Void> softDeleteProperty(String id)
    {
        return propertyRepository.findById(id)
                .doOnNext(property -> property.setDeleted(true))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto).then();
    }


    public Mono<Void> deleteAllProperties()
    {
        // deletes all the properties that have been saved in the property collection
        return propertyRepository.deleteAll();
    }



    public Mono<PropertyDTO> setPropertyStatus(String id, String status)
    {
        return propertyRepository.findById(id)
                .doOnNext(property -> property.setStatus(status))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }



    public Mono<PropertyDTO> createMessage(String propertyId, List<Message> messages)
    {
        return propertyRepository.findById(propertyId)
                .doOnNext(property -> property.setMessages(messages))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }
}


