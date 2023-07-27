package com.dwomo.houseowner.repository;

import com.dwomo.houseowner.events.domain.PropertyCreatedEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends ReactiveMongoRepository<PropertyCreatedEvent, String> {

}

