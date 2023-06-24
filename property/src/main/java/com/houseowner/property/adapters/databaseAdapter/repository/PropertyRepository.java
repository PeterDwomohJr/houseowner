package com.houseowner.property.adapters.databaseAdapter.repository;

import com.houseowner.property.aggregates.entities.Property;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends ReactiveMongoRepository<Property, String> {
}
