package com.dwomo.houseowner.utils;

import com.dwomo.houseowner.dto.PropertyCreatedEventDTO;
import com.dwomo.houseowner.events.domain.PropertyCreatedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PropertyUtils {


    public static PropertyCreatedEventDTO entityToDto(PropertyCreatedEvent propertyCreatedEvent) {

        PropertyCreatedEventDTO propertyCreatedEventDTO = new PropertyCreatedEventDTO();

        BeanUtils.copyProperties(propertyCreatedEvent, propertyCreatedEventDTO);

        return propertyCreatedEventDTO;
    }


    public static PropertyCreatedEvent dtoToEntity(PropertyCreatedEventDTO propertyCreatedEventDTO) {

        PropertyCreatedEvent propertyCreatedEvent = new PropertyCreatedEvent();

        BeanUtils.copyProperties(propertyCreatedEventDTO, propertyCreatedEvent);

        return propertyCreatedEvent;
    }
}
