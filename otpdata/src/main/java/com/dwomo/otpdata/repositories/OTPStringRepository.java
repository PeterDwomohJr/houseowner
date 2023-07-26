package com.dwomo.otpdata.repositories;

import com.dwomo.otpdata.events.domain.OTPCreatedEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OTPStringRepository extends ReactiveMongoRepository<OTPCreatedEvent, String> {
}
