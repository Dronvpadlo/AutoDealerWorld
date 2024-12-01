package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.dto.RegisterDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.entity.enums.ProfileType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@Component
public class UserUtil {

    private final PasswordEncoder passwordEncoder;

    public UserUtil(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User mapUserToEntity(RegisterDTO registerDTO){
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setEmail(registerDTO.getEmail());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setProfileType(ProfileType.BASIC);
        user.setRegisteredAt(LocalDateTime.now());
        System.out.println(user);
        return user;
    }

    public UserDTO mapUserToDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRegisteredAt(LocalDateTime.now());
        userDTO.setProfileType(user.getProfileType());
        userDTO.setRole(
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
        return userDTO;
    }


}
