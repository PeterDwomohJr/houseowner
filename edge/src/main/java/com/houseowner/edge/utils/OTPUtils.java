package com.houseowner.edge.utils;


import com.houseowner.edge.dto.OTPCreatedEventDTO;
import com.houseowner.edge.events.Domain.OTPCreatedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OTPUtils {


    public static OTPCreatedEventDTO entityToDTO(OTPCreatedEvent user)
    {
        OTPCreatedEventDTO otpCreatedEventDTO = new OTPCreatedEventDTO();

        BeanUtils.copyProperties(user, otpCreatedEventDTO);

        return otpCreatedEventDTO;
    }


    public static OTPCreatedEvent dtoToEntity(OTPCreatedEventDTO otpCreatedEventDTO)
    {
        OTPCreatedEvent otpCreatedEvent = new OTPCreatedEvent();

        BeanUtils.copyProperties(otpCreatedEventDTO, otpCreatedEvent);

        return otpCreatedEvent;
    }
}
