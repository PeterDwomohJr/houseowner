package com.houseowner.edge.utils;


import com.houseowner.edge.aggregates.entities.User;
import com.houseowner.edge.dto.UserDTO;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class AppUtils {


    public static UserDTO entityToDTO(User user)
    {
        UserDTO userDTO = new UserDTO();

        BeanUtils.copyProperties(user, userDTO);

        return userDTO;
    }


    public static User dtoToEntity(UserDTO userDTO)
    {
        User user = new User();

        BeanUtils.copyProperties(userDTO, user);

        return user;
    }
}
