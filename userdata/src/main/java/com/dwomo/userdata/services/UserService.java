package com.dwomo.userdata.services;


import com.dwomo.userdata.dto.UserCreatedEventDTO;
import com.dwomo.userdata.repositories.UserRepository;
import com.dwomo.userdata.utils.UserUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {

    private final UserRepository userRepository;



    public UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }



    public Flux<UserCreatedEventDTO> getUsers()
    {
        return userRepository.findAll().map(UserUtils::entityToDTO);
    }



    public Mono<UserCreatedEventDTO> getUser(String id)
    {
        return userRepository.findById(id).map(UserUtils::entityToDTO);
    }



    public Mono<Long> getCount()
    {
        return userRepository.count();
    }



    public Mono<UserCreatedEventDTO> save(Mono<UserCreatedEventDTO> userCreatedEventDTOMono)
    {
        return userCreatedEventDTOMono.map(UserUtils::dtoToEntity)
                .flatMap(userRepository::insert)
                .map(UserUtils::entityToDTO);
    }




    public Mono<UserCreatedEventDTO> update(String id,Mono<UserCreatedEventDTO> userCreatedEventDTOMono)
    {
        return userRepository.findById(id)
                .flatMap(user -> userCreatedEventDTOMono.map(UserUtils::dtoToEntity))
                .doOnNext(userEntity -> userEntity.setId(id))
                .flatMap(userRepository::save)
                .map(UserUtils::entityToDTO);
    }



    public Mono<Void> deleteUsers()
    {
        return userRepository.deleteAll();
    }



    public Mono<Void> deleteUser(String id)
    {
        return userRepository.deleteById(id);
    }
}
