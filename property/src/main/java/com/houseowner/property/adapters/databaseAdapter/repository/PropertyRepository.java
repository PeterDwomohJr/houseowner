package com.houseowner.property.adapters.databaseAdapter.repository;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.aggregates.entities.Property;
import org.springframework.data.domain.Range;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface PropertyRepository extends ReactiveMongoRepository<Property, String> {
    Flux<PropertyDTO> findByPriceBetween(Range<Double> priceRange);
}
