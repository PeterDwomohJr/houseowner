package com.houseowner.edge.repositories;

import com.houseowner.edge.events.Domain.OTPCreatedEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface OTPCacheRepository extends ReactiveMongoRepository<OTPCreatedEvent, String> {

    Mono<OTPCreatedEvent> findByOtpString(String otpString);

    Mono<Void> deleteByPhoneNumber(String phoneNumber);
}


