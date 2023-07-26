package com.dwomo.userdata.utils;

import com.dwomo.userdata.dto.UserCreatedEventDTO;
import com.dwomo.userdata.events.domain.UserCreatedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserDataUtils {

    public static UserCreatedEventDTO entityToDTO(UserCreatedEvent userCreatedEvent)
    {
        UserCreatedEventDTO userCreatedEventDTO = new UserCreatedEventDTO();

        BeanUtils.copyProperties(userCreatedEvent, userCreatedEventDTO);

        return userCreatedEventDTO;
    }


    public static UserCreatedEvent dtoToEntity(UserCreatedEventDTO userCreatedEventDTO)
    {
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();

        BeanUtils.copyProperties(userCreatedEventDTO, userCreatedEvent);

        return userCreatedEvent;
    }
}
