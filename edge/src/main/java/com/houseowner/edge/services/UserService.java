package com.houseowner.edge.services;

import com.houseowner.edge.dto.UserCreatedEventDTO;
import com.houseowner.edge.repositories.UserRepository;
import com.houseowner.edge.utils.UserUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final String ACTIVE_STATUS = "ACTIVE";
    private final String PENDING_STATUS = "PENDING";
    private final UserRepository  userRepository;



    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    public Flux<UserCreatedEventDTO> getUsers()
    {
        // returns all the users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO);
    }



    public Mono<UserCreatedEventDTO> getUser(String id)
    {
        // returns the user with the given id
        return userRepository.findById(id).map(UserUtils::entityToDTO);
    }



    public Mono<UserCreatedEventDTO> getUserByPhoneNumber(String phoneNumber)
    {
        // returns the user with the given phone number
        return userRepository.findByPhoneNumber(phoneNumber).map(UserUtils::entityToDTO);
    }




    public Mono<Long> getCount()
    {
        // returns the number of users in the repository
        return userRepository.count();
    }


    public Flux<UserCreatedEventDTO> getActive()
    {
        // returns all the active users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> property.getStatus().equals(ACTIVE_STATUS));
    }



    public Flux<UserCreatedEventDTO> getPending()
    {
        // returns all the pending users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> property.getStatus().equals(PENDING_STATUS));
    }



    public Flux<UserCreatedEventDTO> getSoftDeleted()
    {
        // returns all the soft deleted users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(UserCreatedEventDTO::isDeleted);
    }



    public Flux<UserCreatedEventDTO> getNonSoftDeleted()
    {
        // returns all the non-soft deleted users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> !property.isDeleted());
    }



    public Mono<UserCreatedEventDTO> save(Mono<UserCreatedEventDTO> propertyDTOMono)
    {
        // returns the saved user
        return propertyDTOMono.map(UserUtils::dtoToEntity)
                .flatMap(userRepository::insert)
                .map(UserUtils::entityToDTO);
    }


    public Mono<UserCreatedEventDTO> update(String id, Mono<UserCreatedEventDTO> propertyDTOMono)
    {
        // returns the updated user
        return userRepository.findById(id)
                .flatMap(property -> propertyDTOMono.map(UserUtils::dtoToEntity))
                .doOnNext(propertyEntity -> propertyEntity.setId(id))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO);
    }


    public Mono<Void> delete(String id)
    {
        // deletes the user with the given id from the repository
        return userRepository.deleteById(id);
    }



    public Mono<Void> softDelete(String id)
    {
        // deletes the user with the given id by marking the delete status as true
        return userRepository.findById(id)
                .doOnNext(property -> property.setDeleted(true))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO).then();
    }


    public Mono<Void> deleteAll()
    {
        // deletes all users
        return userRepository.deleteAll();
    }



    public Mono<UserCreatedEventDTO> setUserStatus(String id, String status)
    {
        // sets the status of the user
        return userRepository.findById(id)
                .doOnNext(property -> property.setStatus(status))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO);
    }
}