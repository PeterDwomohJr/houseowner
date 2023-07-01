package com.dwomo.houseowner.service;

import com.dwomo.houseowner.dto.PropertyDTO;
import com.dwomo.houseowner.repository.PropertyRepository;
import com.dwomo.houseowner.utils.AppUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.math.BigDecimal;

@Service
public class PropertyService {

    private final String ACTIVE_STATUS = "ACTIVE";
    private final PropertyRepository propertyRepository;


    public PropertyService(PropertyRepository propertyRepository)
    {
        this.propertyRepository = propertyRepository;
    }


    public Flux<PropertyDTO> getProperties()
    {
        return propertyRepository.findAll().map(AppUtils::entityToDto);
    }


    public Mono<PropertyDTO> getProperty(String id)
    {
        return propertyRepository.findById(id).map(AppUtils::entityToDto);
    }


    public Mono<Long> getPropertyCount()
    {
        return propertyRepository.count();
    }


    public Flux<PropertyDTO> getActiveProperties()
    {
        return propertyRepository.findAll().map(AppUtils::entityToDto)
                .filter(property -> property.getStatus().equals(ACTIVE_STATUS));
    }



    public Flux<PropertyDTO> getNonDeletedProperties()
    {
        return propertyRepository.findAll().map(AppUtils::entityToDto)
                .filter(property -> !property.isDeleted());
    }


    public Flux<PropertyDTO> getPropertiesBetweenPriceRange(BigDecimal min, BigDecimal max)
    {
        return propertyRepository.findAll().map(AppUtils::entityToDto)
        .filter(property -> property.getPrice().compareTo(min) >= 0 && property.getPrice().compareTo(max) <= 0);
    }



    public Mono<PropertyDTO> saveProperty(Mono<PropertyDTO> propertyDTOMono)
    {
        return propertyDTOMono.map(AppUtils::dtoToEntity)
                .flatMap(propertyRepository::insert)
                .map(AppUtils::entityToDto);
    }


    public Mono<PropertyDTO> updateProperty(String id, Mono<PropertyDTO> propertyDTOMono)
    {
        return propertyRepository.findById(id)
                .flatMap(property -> propertyDTOMono.map(AppUtils::dtoToEntity))
                .doOnNext(propertyEntity -> propertyEntity.setId(id))
                .flatMap(propertyRepository::save)
                .map(AppUtils::entityToDto);
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
                .map(AppUtils::entityToDto).then();
    }


    public Mono<Void> deleteAllProperties()
    {
        return propertyRepository.deleteAll();
    }



    public Mono<PropertyDTO> setPropertyStatus(String id, String status)
    {
        return propertyRepository.findById(id)
                .doOnNext(property -> property.setStatus(status))
                .flatMap(propertyRepository::save)
                .map(AppUtils::entityToDto);
    }
}


