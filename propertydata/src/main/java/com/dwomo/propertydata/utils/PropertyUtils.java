package com.dwomo.propertydata.utils;

import com.dwomo.propertydata.dto.PropertyCreatedEventDTO;
import com.dwomo.propertydata.events.domain.PropertyCreatedEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PropertyUtils {



    public static PropertyCreatedEventDTO entityToDTO(PropertyCreatedEvent propertyCreatedEvent)
    {
        PropertyCreatedEventDTO propertyCreatedEventDTO = new PropertyCreatedEventDTO();

        BeanUtils.copyProperties(propertyCreatedEvent, propertyCreatedEventDTO);

        return propertyCreatedEventDTO;
    }




    public static PropertyCreatedEvent dtoToEntity(PropertyCreatedEventDTO propertyCreatedEventDTO)
    {
        PropertyCreatedEvent propertyCreatedEvent = new PropertyCreatedEvent();

        BeanUtils.copyProperties(propertyCreatedEventDTO, propertyCreatedEvent);

        return propertyCreatedEvent;
    }
}
