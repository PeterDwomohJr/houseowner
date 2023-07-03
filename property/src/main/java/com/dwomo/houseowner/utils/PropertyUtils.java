package com.dwomo.houseowner.utils;

import com.dwomo.houseowner.aggregate.entities.Property;
import com.dwomo.houseowner.dto.PropertyDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class PropertyUtils {


    public static PropertyDTO entityToDto(Property property) {

        PropertyDTO propertyDTO = new PropertyDTO();

        BeanUtils.copyProperties(property, propertyDTO);

        return propertyDTO;
    }


    public static Property dtoToEntity(PropertyDTO propertyDTO) {

        Property property = new Property();

        BeanUtils.copyProperties(propertyDTO, property);

        return property;
    }
}
