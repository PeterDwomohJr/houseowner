package com.houseowner.property.utilities;

import com.houseowner.property.DTOs.PropertyDTO;
import com.houseowner.property.aggregates.entities.Property;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {


    public static PropertyDTO entityToDTO(Property property) {

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
