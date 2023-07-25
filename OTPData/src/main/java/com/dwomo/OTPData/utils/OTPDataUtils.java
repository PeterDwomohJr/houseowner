package com.dwomo.OTPData.utils;

import com.dwomo.OTPData.dto.OTPCreatedEventDTO;
import com.dwomo.OTPData.events.domain.OTPCreatedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class OTPDataUtils {

    public static OTPCreatedEventDTO entityToDTO(OTPCreatedEvent otpCreatedEvent)
    {
        OTPCreatedEventDTO otpCreatedEventDTO = new OTPCreatedEventDTO();

        BeanUtils.copyProperties(otpCreatedEvent, otpCreatedEventDTO);

        return otpCreatedEventDTO;
    }


    public static OTPCreatedEvent dtoToEntity(OTPCreatedEventDTO otpCreatedEventDTO)
    {
        OTPCreatedEvent otpCreatedEvent = new OTPCreatedEvent();

        BeanUtils.copyProperties(otpCreatedEventDTO, otpCreatedEvent);

        return otpCreatedEvent;
    }
}
