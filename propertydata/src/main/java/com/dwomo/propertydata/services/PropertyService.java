package com.dwomo.propertydata.services;


import com.dwomo.propertydata.dto.PropertyCreatedEventDTO;
import com.dwomo.propertydata.repositories.PropertyRepository;
import com.dwomo.propertydata.utils.PropertyUtils;
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



    public Flux<PropertyCreatedEventDTO> getProperties()
    {
        return propertyRepository.findAll().map(PropertyUtils::entityToDTO);
    }



    public Mono<PropertyCreatedEventDTO> getProperty(String id)
    {
        return propertyRepository.findById(id).map(PropertyUtils::entityToDTO);
    }



    public Mono<Long> getCount()
    {
        return propertyRepository.count();
    }


    public Mono<PropertyCreatedEventDTO> save(Mono<PropertyCreatedEventDTO> propertyCreatedEventDTOMono)
    {
        return propertyCreatedEventDTOMono.map(PropertyUtils::dtoToEntity)
                .flatMap(propertyRepository::insert)
                .map(PropertyUtils::entityToDTO);
    }
}
