package com.dwomo.houseowner.utils;

import com.dwomo.houseowner.dto.PropertyEventDTO;
import com.dwomo.houseowner.events.domain.PropertyEvent;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PropertyUtils {


    public static PropertyEventDTO entityToDto(PropertyEvent propertyEvent) {

        PropertyEventDTO propertyEventDTO = new PropertyEventDTO();

        BeanUtils.copyProperties(propertyEvent, propertyEventDTO);

        return propertyEventDTO;
    }


    public static PropertyEvent dtoToEntity(PropertyEventDTO propertyEventDTO) {

        PropertyEvent propertyEvent = new PropertyEvent();

        BeanUtils.copyProperties(propertyEventDTO, propertyEvent);

        return propertyEvent;
    }
}
