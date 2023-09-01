package com.dwomo.houseowner.repository;

import com.dwomo.houseowner.dto.PropertyEventDTO;
import com.dwomo.houseowner.events.domain.PropertyEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface PropertyRepository extends ReactiveMongoRepository<PropertyEvent, String> {
}

