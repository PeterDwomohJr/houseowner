package com.dwomo.userdata.repositories;

import com.dwomo.userdata.events.domain.UserCreatedEvent;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends ReactiveMongoRepository<UserCreatedEvent, String> {
}
