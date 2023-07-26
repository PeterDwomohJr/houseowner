package com.dwomo.otpdata.services;


import com.dwomo.otpdata.dto.OTPCreatedEventDTO;
import com.dwomo.otpdata.repositories.OTPStringRepository;
import com.dwomo.otpdata.utils.OTPDataUtils;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class OTPService {

    private final OTPStringRepository otpStringRepository;


    public OTPService(OTPStringRepository otpStringRepository)
    {
        this.otpStringRepository = otpStringRepository;
    }



    public Flux<OTPCreatedEventDTO> getOTPs()
    {
        return otpStringRepository.findAll().map(OTPDataUtils::entityToDTO);
    }



    public Mono<Long> getCount()
    {
        return otpStringRepository.count();
    }



    public Mono<OTPCreatedEventDTO> saveOTP(Mono<OTPCreatedEventDTO> otpCreatedEventDTOMono)
    {
        return otpCreatedEventDTOMono.map(OTPDataUtils::dtoToEntity)
                .flatMap(otpStringRepository::insert)
                .map(OTPDataUtils::entityToDTO);
    }
}
