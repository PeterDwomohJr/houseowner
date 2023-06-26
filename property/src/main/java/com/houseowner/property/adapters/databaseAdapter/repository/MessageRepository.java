package com.houseowner.property.adapters.databaseAdapter.repository;

import com.houseowner.property.aggregates.valueObjects.Message;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface MessageRepository extends ReactiveMongoRepository<Message, String> {
}
