package com.dwomo.houseowner.repository;

import com.dwomo.houseowner.aggregate.entities.Property;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PropertyRepository extends ReactiveMongoRepository<Property, String> {

}

