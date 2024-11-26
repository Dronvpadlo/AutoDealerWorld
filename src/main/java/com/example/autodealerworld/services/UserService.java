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
        System.out.println(registerDTO.getPassword());
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            throw new RuntimeException("Password not equal");
        }
        User user = userUtil.mapNewUserToEntity(registerDTO);
        user.setRole(UserRole.BUYER);

        userRepository.save(user);
        return userUtil.mapUserToDTO(user);
    }

    public UserDTO createManager(RegisterDTO registerDTO){
        System.out.println(registerDTO.getPassword());
        if (!registerDTO.getPassword().equals(registerDTO.getConfirmPassword())){
            throw new RuntimeException("Password not equal");
        }
        User user = userUtil.mapNewUserToEntity(registerDTO);
        user.setRole(UserRole.MANAGER);

        userRepository.save(user);
        return userUtil.mapUserToDTO(user);
    }


    public List<UserDTO> findUsers(){
        return userRepository.findAll().stream().map(userUtil::mapUserToDTO).toList();
    }

    public User findUserById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
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
