package com.dwomo.houseowner.service;

import com.dwomo.houseowner.dto.PropertyEventDTO;
import com.dwomo.houseowner.events.domain.Message;
import com.dwomo.houseowner.events.publisher.EventPublisher;
import com.dwomo.houseowner.repository.PropertyRepository;
import com.dwomo.houseowner.utils.PropertyUtils;
import org.springframework.stereotype.Service;
import org.testng.annotations.Test;
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

    private String PROPERTY_ID = "64e2260672d21c199a99eb0e";
    private static final String ACTIVE = "ACTIVE";
    private static final String PENDING = "PENDING";
    private static final String TOPIC_NAME = "property-created";
    private final EventPublisher eventPublisher;
    private final PropertyRepository propertyRepository;


    /**
     *
     */
    public PropertyService(EventPublisher eventPublisher, PropertyRepository propertyRepository)
    {
        // perform constructor injection
        this.eventPublisher = eventPublisher;
        this.propertyRepository = propertyRepository;
    }


    public Flux<PropertyEventDTO> getProperties()
    {
        // returns all the properties in the property collection
        return propertyRepository.findAll().map(PropertyUtils::entityToDto);
    }


    public Mono<PropertyEventDTO> getProperty()
    {
        return propertyRepository.findById(PROPERTY_ID).map(PropertyUtils::entityToDto);
    }


    public Mono<Long> getPropertyCount()
    {
        return propertyRepository.count();
    }


    public Flux<PropertyEventDTO> getActiveProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> property.getStatus().equals(ACTIVE));
    }



    public Flux<PropertyEventDTO> getPendingProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> property.getStatus().equals(PENDING));
    }



    public Flux<PropertyEventDTO> getSoftDeletedProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(PropertyEventDTO::isDeleted);
    }



    public Flux<PropertyEventDTO> getNonSoftDeleted()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> !property.isDeleted());
    }


    public Flux<PropertyEventDTO> getPropertiesBetweenPriceRange(BigDecimal min, BigDecimal max)
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
        .filter(property -> property.getPrice().compareTo(min) >= 0 && property.getPrice().compareTo(max) <= 0);
    }



    public Mono<PropertyEventDTO> save(Mono<PropertyEventDTO> propertyEventDTOMono)
    {
        return propertyEventDTOMono.map(PropertyUtils::dtoToEntity)
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto)
                .doOnNext(propertyEventDTO -> PROPERTY_ID = propertyEventDTO.getId())
                .doOnNext(propertyEventDTO -> System.out.println(propertyEventDTO.getId()))
                .doOnNext(propertyEventDTO -> eventPublisher.publishToBroker(propertyEventDTO, TOPIC_NAME));
    }


    public Mono<PropertyEventDTO> update(Mono<PropertyEventDTO> propertyDTOMono)
    {
        return propertyRepository.findById(PROPERTY_ID)
                .flatMap(property -> propertyDTOMono.map(PropertyUtils::dtoToEntity))
                .doOnNext(propertyEntity -> propertyEntity.setId(PROPERTY_ID))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }


    public Mono<Void> delete()
    {
        // delete a property from the repository
        return propertyRepository.deleteById(PROPERTY_ID);
    }



    public Mono<PropertyEventDTO> softDelete()
    {
        return propertyRepository.findById(PROPERTY_ID)
                .doOnNext(property -> property.setDeleted(true))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }


    public Mono<Void> deleteProperties()
    {
        // deletes all the properties that have been saved in the property collection
        return propertyRepository.deleteAll();
    }



    public Mono<PropertyEventDTO> setPropertyStatus(String status)
    {
        return propertyRepository.findById(PROPERTY_ID)
                .doOnNext(property -> property.setStatus(status))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto)
                .doOnNext(propertyCreatedEventDTO -> eventPublisher.publishToBroker(propertyCreatedEventDTO, "property active"));
    }



    public Mono<PropertyEventDTO> createMessage(List<Message> messages)
    {
        return propertyRepository.findById(PROPERTY_ID)
                .doOnNext(property -> property.setMessages(messages))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }


    public Flux<PropertyEventDTO> searchProperty(String locationName) {
        String lowerCaseLocationName = locationName.toLowerCase();

        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> property.getLocation().getName().toLowerCase().equals(lowerCaseLocationName));
    }
}


