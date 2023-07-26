package com.dwomo.otpdata.utils;

import com.dwomo.otpdata.dto.OTPCreatedEventDTO;
import com.dwomo.otpdata.events.domain.OTPCreatedEvent;
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
