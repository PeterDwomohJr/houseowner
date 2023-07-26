package com.dwomo.userdata.services;


import com.dwomo.userdata.dto.UserCreatedEventDTO;
import com.dwomo.userdata.repositories.UserRepository;
import com.dwomo.userdata.utils.UserDataUtils;
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



    public Flux<UserCreatedEventDTO> getOTPs()
    {
        return userRepository.findAll().map(UserDataUtils::entityToDTO);
    }



    public Mono<Long> getCount()
    {
        return userRepository.count();
    }



    public Mono<UserCreatedEventDTO> saveOTP(Mono<UserCreatedEventDTO> otpCreatedEventDTOMono)
    {
        return otpCreatedEventDTOMono.map(UserDataUtils::dtoToEntity)
                .flatMap(userRepository::insert)
                .map(UserDataUtils::entityToDTO);
    }
}
