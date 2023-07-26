package com.dwomo.propertydata.repositories;

import com.dwomo.propertydata.events.domain.PropertyCreatedEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends ReactiveMongoRepository<PropertyCreatedEvent, String> {
}
