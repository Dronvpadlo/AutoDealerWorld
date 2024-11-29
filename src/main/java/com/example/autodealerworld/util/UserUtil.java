package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.dto.RegisterDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.entity.enums.ProfileType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class UserUtil {

    private final PasswordEncoder passwordEncoder;

    public UserUtil(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapNewUserToEntity(RegisterDTO registerDTO){
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setProfileType(ProfileType.BASIC);
        System.out.println(user);
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
