package com.houseowner.edge.services;

import com.houseowner.edge.dto.UserDTO;
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



    public Flux<UserDTO> getUsers()
    {
        // returns all the users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO);
    }



    public Mono<UserDTO> getUser(String id)
    {
        // returns the user with the given id
        return userRepository.findById(id).map(UserUtils::entityToDTO);
    }



    public Mono<UserDTO> getUserByPhoneNumber(String phoneNumber)
    {
        // returns the user with the given phone number
        return userRepository.findByPhoneNumber(phoneNumber).map(UserUtils::entityToDTO);
    }




    public Mono<Long> getCount()
    {
        // returns the number of users in the repository
        return userRepository.count();
    }


    public Flux<UserDTO> getActiveUsers()
    {
        // returns all the active users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> property.getStatus().equals(ACTIVE_STATUS));
    }



    public Flux<UserDTO> getPendingUsers()
    {
        // returns all the pending users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> property.getStatus().equals(PENDING_STATUS));
    }



    public Flux<UserDTO> getSoftDeletedUsers()
    {
        // returns all the soft deleted users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(UserDTO::isDeleted);
    }



    public Flux<UserDTO> getNonSoftDeletedUsers()
    {
        // returns all the non-soft deleted users in the repository
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> !property.isDeleted());
    }



    public Mono<UserDTO> saveUser(Mono<UserDTO> propertyDTOMono)
    {
        // returns the saved user
        return propertyDTOMono.map(UserUtils::dtoToEntity)
                .flatMap(userRepository::insert)
                .map(UserUtils::entityToDTO);
    }


    public Mono<UserDTO> updateUser(String id, Mono<UserDTO> propertyDTOMono)
    {
        // returns the updated user
        return userRepository.findById(id)
                .flatMap(property -> propertyDTOMono.map(UserUtils::dtoToEntity))
                .doOnNext(propertyEntity -> propertyEntity.setId(id))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO);
    }


    public Mono<Void> deleteUser(String id)
    {
        // deletes the user with the given id from the repository
        return userRepository.deleteById(id);
    }



    public Mono<Void> softDeleteUser(String id)
    {
        // deletes the user with the given id by marking the delete status as true
        return userRepository.findById(id)
                .doOnNext(property -> property.setDeleted(true))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO).then();
    }


    public Mono<Void> deleteAllUsers()
    {
        // deletes all users
        return userRepository.deleteAll();
    }



    public Mono<UserDTO> setUserStatus(String id, String status)
    {
        // sets the status of the user
        return userRepository.findById(id)
                .doOnNext(property -> property.setStatus(status))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO);
    }
}