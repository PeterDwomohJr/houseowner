package com.dwomo.houseowner.service;

import com.dwomo.houseowner.aggregate.valueObject.Message;
import com.dwomo.houseowner.dto.PropertyCreatedEventDTO;
import com.dwomo.houseowner.events.publisher.EventPublisher;
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

    private static final String ACTIVE_STATUS = "ACTIVE";
    private static final String PENDING_STATUS = "PENDING";
    private static final String TOPIC_NAME = "property-created-topic";
    private final EventPublisher eventPublisher;
    private final PropertyRepository propertyRepository;



    /**
     *
     */
    public PropertyService(EventPublisher eventPublisher, PropertyRepository propertyRepository)
    {   // perform constructor injection
        this.eventPublisher = eventPublisher;
        this.propertyRepository = propertyRepository;
    }


    public Flux<PropertyCreatedEventDTO> getProperties()
    {
        // returns all the properties in the property collection
        return propertyRepository.findAll().map(PropertyUtils::entityToDto);
    }


    public Mono<PropertyCreatedEventDTO> getProperty(String id)
    {
        return propertyRepository.findById(id).map(PropertyUtils::entityToDto);
    }


    public Mono<Long> getPropertyCount()
    {
        return propertyRepository.count();
    }


    public Flux<PropertyCreatedEventDTO> getActiveProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> property.getStatus().equals(ACTIVE_STATUS));
    }



    public Flux<PropertyCreatedEventDTO> getPendingProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> property.getStatus().equals(PENDING_STATUS));
    }



    public Flux<PropertyCreatedEventDTO> getSoftDeletedProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(PropertyCreatedEventDTO::isDeleted);
    }



    public Flux<PropertyCreatedEventDTO> getNonSoftDeleted()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
                .filter(property -> !property.isDeleted());
    }


    public Flux<PropertyCreatedEventDTO> getPropertiesBetweenPriceRange(BigDecimal min, BigDecimal max)
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDto)
        .filter(property -> property.getPrice().compareTo(min) >= 0 && property.getPrice().compareTo(max) <= 0);
    }



    public Mono<PropertyCreatedEventDTO> save(Mono<PropertyCreatedEventDTO> propertyCreatedEventDTOMono)
    {
        return propertyCreatedEventDTOMono.map(PropertyUtils::dtoToEntity)
                .flatMap(propertyRepository::insert)
                .map(PropertyUtils::entityToDto)
                .doOnNext(propertyCreatedDTO -> eventPublisher.publishToBroker(propertyCreatedDTO, TOPIC_NAME));
    }


    public Mono<PropertyCreatedEventDTO> update(String id, Mono<PropertyCreatedEventDTO> propertyDTOMono)
    {
        return propertyRepository.findById(id)
                .flatMap(property -> propertyDTOMono.map(PropertyUtils::dtoToEntity))
                .doOnNext(propertyEntity -> propertyEntity.setId(id))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }


    public Mono<Void> delete(String id)
    {
        return propertyRepository.deleteById(id);
    }



    public Mono<Void> softDelete(String id)
    {
        return propertyRepository.findById(id)
                .doOnNext(property -> property.setDeleted(true))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto).then();
    }


    public Mono<Void> deleteProperties()
    {
        // deletes all the properties that have been saved in the property collection
        return propertyRepository.deleteAll();
    }



    public Mono<PropertyCreatedEventDTO> setPropertyStatus(String id, String status)
    {
        return propertyRepository.findById(id)
                .doOnNext(property -> property.setStatus(status))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }



    public Mono<PropertyCreatedEventDTO> createMessage(String propertyId, List<Message> messages)
    {
        return propertyRepository.findById(propertyId)
                .doOnNext(property -> property.setMessages(messages))
                .flatMap(propertyRepository::save)
                .map(PropertyUtils::entityToDto);
    }
}


