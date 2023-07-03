package com.houseowner.edge.services;

import com.houseowner.edge.dto.UserDTO;
import com.houseowner.edge.repositories.UserRepository;
import com.houseowner.edge.utils.UserUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

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
        return userRepository.findAll().map(UserUtils::entityToDTO);
    }



    public Mono<UserDTO> getUser(String id)
    {
        return userRepository.findById(id).map(UserUtils::entityToDTO);
    }




    public Mono<Long> getUserCount()
    {
        return userRepository.count();
    }


    public Flux<UserDTO> getActiveUsers()
    {
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> property.getStatus().equals(ACTIVE_STATUS));
    }



    public Flux<UserDTO> getPendingUsers()
    {
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> property.getStatus().equals(PENDING_STATUS));
    }



    public Flux<UserDTO> getSoftDeletedUsers()
    {
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(UserDTO::isDeleted);
    }



    public Flux<UserDTO> getNonSoftDeletedUsers()
    {
        return userRepository.findAll().map(UserUtils::entityToDTO)
                .filter(property -> !property.isDeleted());
    }



    public Mono<UserDTO> saveUser(Mono<UserDTO> propertyDTOMono)
    {
        return propertyDTOMono.map(UserUtils::dtoToEntity)
                .flatMap(userRepository::insert)
                .map(UserUtils::entityToDTO);
    }


    public Mono<UserDTO> updateUser(String id, Mono<UserDTO> propertyDTOMono)
    {
        return userRepository.findById(id)
                .flatMap(property -> propertyDTOMono.map(UserUtils::dtoToEntity))
                .doOnNext(propertyEntity -> propertyEntity.setId(id))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO);
    }


    public Mono<Void> deleteUser(String id)
    {
        return userRepository.deleteById(id);
    }



    public Mono<Void> softDeleteUser(String id)
    {
        return userRepository.findById(id)
                .doOnNext(property -> property.setDeleted(true))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO).then();
    }


    public Mono<Void> deleteAllUsers()
    {
        return userRepository.deleteAll();
    }



    public Mono<UserDTO> setUserStatus(String id, String status)
    {
        return userRepository.findById(id)
                .doOnNext(property -> property.setStatus(status))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO);
    }
}