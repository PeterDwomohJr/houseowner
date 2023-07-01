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


    public Mono<PropertyDTO> getProperties(String id)
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


    public Flux<PropertyDTO> getPropertiesBetweenPriceRange(BigDecimal min, BigDecimal max)
    {
        return propertyRepository.findAll().map(AppUtils::entityToDto)
        .filter(property -> property.getPrice().compareTo(min) >= 0 && property.getPrice().compareTo(max) <= 0);
    }



    public Mono<PropertyDTO> saveProperty(Mono<PropertyDTO> productDtoMono)
    {
        return productDtoMono.map(AppUtils::dtoToEntity)
                .flatMap(propertyRepository::insert)
                .map(AppUtils::entityToDto);
    }


    public Mono<PropertyDTO> updateProperty(String id, Mono<PropertyDTO> productDtoMono)
    {
        return propertyRepository.findById(id)
                .flatMap(product -> productDtoMono.map(AppUtils::dtoToEntity))
                .doOnNext(productEntity -> productEntity.setId(id))
                .flatMap(propertyRepository::save)
                .map(AppUtils::entityToDto);
    }


    public Mono<Void> deleteProperty(String id)
    {
        return propertyRepository.deleteById(id);
    }


    public Mono<Void> deleteAllProperties()
    {
        return propertyRepository.deleteAll();
    }


    public Mono<PropertyDTO> setPropertyStatusActive(String id)
    {
        return propertyRepository.findById(id)
                .doOnNext(property -> property.setStatus(ACTIVE_STATUS))
                .flatMap(propertyRepository::save)
                .map(AppUtils::entityToDto);
    }
}


