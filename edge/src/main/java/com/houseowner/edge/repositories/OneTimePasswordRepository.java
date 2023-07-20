package com.houseowner.edge.repositories;

import com.houseowner.edge.aggregates.entities.OneTimePassword;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface OneTimePasswordRepository extends ReactiveMongoRepository<OneTimePassword, String> {

    Mono<OneTimePassword> findOneTimePassword(String oneTimePassword);
    void deleteOneTimePassword(String oneTimePassword);
}


