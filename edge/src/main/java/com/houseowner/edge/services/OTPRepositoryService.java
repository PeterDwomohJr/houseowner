package com.houseowner.edge.services;


import com.houseowner.edge.dto.OTPCreatedEventDTO;
import com.houseowner.edge.repositories.OTPCacheRepository;
import com.houseowner.edge.utils.OTPUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OTPRepositoryService {

    private final OTPCacheRepository OTPCacheRepository;



    public OTPRepositoryService(OTPCacheRepository OTPCacheRepository)
    {
        this.OTPCacheRepository = OTPCacheRepository;
    }



    public Flux<OTPCreatedEventDTO> getOTPs()
    {
        return OTPCacheRepository.findAll().map(OTPUtils::entityToDTO);
    }



    public Mono<Long> getCount()
    {
        return OTPCacheRepository.count();
    }




    public Mono<OTPCreatedEventDTO> saveOTP(Mono<OTPCreatedEventDTO> otpCreatedEventDTOMono)
    {
        return otpCreatedEventDTOMono.map(OTPUtils::dtoToEntity)
                .flatMap(OTPCacheRepository::insert)
                .map(OTPUtils::entityToDTO);
    }
}
