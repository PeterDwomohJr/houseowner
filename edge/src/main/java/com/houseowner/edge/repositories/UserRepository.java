package com.houseowner.edge.repositories;

import com.houseowner.edge.aggregates.entities.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UserRepository extends ReactiveMongoRepository<User, String> {

    public Mono<User> findByPhoneNumber(String phoneNumber);
}
