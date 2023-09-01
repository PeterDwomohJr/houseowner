package com.houseowner.edge.utils;


import com.houseowner.edge.aggregates.entities.User;
import com.houseowner.edge.dto.UserCreatedEventDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class UserUtils {


    public static UserCreatedEventDTO entityToDTO(User user)
    {
        UserCreatedEventDTO userCreatedEventDTO = new UserCreatedEventDTO();

        BeanUtils.copyProperties(user, userCreatedEventDTO);

        return userCreatedEventDTO;
    }


    public static User dtoToEntity(UserCreatedEventDTO userCreatedEventDTO)
    {
        User user = new User();

        BeanUtils.copyProperties(userCreatedEventDTO, user);

        return user;
    }
}
