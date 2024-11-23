package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.dto.UserDTO;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    public User mapUserToEntity(UserDTO userDTO){
        User user = new User();
        user.setUsername(userDTO.getUsername());
        user.setEmail(userDTO.getUsername());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setRole(userDTO.getRole());
        user.setProfileType(userDTO.getProfileType());
        return user;
    }

    public UserDTO mapUserToDTO(User user){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(user.getRole());
        userDTO.setProfileType(user.getProfileType());
        userDTO.setUserId(user.getId());
        return userDTO;
    }
}
