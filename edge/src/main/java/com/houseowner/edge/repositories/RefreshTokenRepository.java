package com.houseowner.edge.repositories;

import com.houseowner.edge.aggregates.entities.RefreshToken;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RefreshTokenRepository extends ReactiveMongoRepository<RefreshToken, String> {
}
