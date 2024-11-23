package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.dto.RegisterDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.entity.enums.ProfileType;
import com.example.autodealerworld.entity.enums.UserRole;
import com.example.autodealerworld.repository.UserRepository;
import com.example.autodealerworld.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final UserUtil userUtil;

    public UserDTO userRegister(RegisterDTO registerDTO){
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            throw new RuntimeException("Password not equal");
        }
        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(registerDTO.getPassword());
        user.setEmail(registerDTO.getEmail());
        user.setPhoneNumber(registerDTO.getPhoneNumber());
        user.setRole(UserRole.BUYER);
        user.setProfileType(ProfileType.BASIC);

        userRepository.save(user);
        return userUtil.mapUserToDTO(user);
    }

    public List<UserDTO> findUsers(){
        return userRepository.findAll().stream().map(userUtil::mapUserToDTO).toList();
    }

    public UserDTO beAsSeller(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        if (user.getEmail() == null || user.getPhoneNumber() == null){
            throw new RuntimeException("Email and phone number are required to become a seller");
        }
        user.setRole(UserRole.SELLER);
        userRepository.save(user);
        return userUtil.mapUserToDTO(user);
    }

    public UserDTO buyPremium(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        if (!user.getRole().equals(UserRole.SELLER)){
            throw new RuntimeException("Only Seller can buy Premium");
        }
        user.setProfileType(ProfileType.PREMIUM);
        userRepository.save(user);
        return userUtil.mapUserToDTO(user);
    }


    public UserDTO changeUserRoleOrProfileType(Long userId, UserRole newRole, ProfileType newProfileType){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        if (newRole != null){
            user.setRole(newRole);
        }
        if (newProfileType != null) {
            user.setProfileType(newProfileType);
        }
        userRepository.save(user);

        return userUtil.mapUserToDTO(user);
    }
}
