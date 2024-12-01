package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.dto.RegisterDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.entity.enums.ProfileType;
import com.example.autodealerworld.entity.enums.RoleName;
import com.example.autodealerworld.repository.UserRepository;
import com.example.autodealerworld.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserUtil userUtil;

    public UserDTO userRegister(RegisterDTO registerDTO){
        if (userRepository.existsByEmail(registerDTO.getEmail())){
            throw new IllegalArgumentException("Email is already taken");
        }

        User user = userRepository.save(userUtil.mapUserToEntity(registerDTO));
        return userUtil.mapUserToDTO(user);

    }

    public UserDTO createManager(RegisterDTO registerDTO){
        System.out.println(registerDTO.getPassword());
        User user = userUtil.mapUserToEntity(registerDTO);
        //user.setRoles();
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
        //user.setRole(UserRole.SELLER);
        userRepository.save(user);
        return userUtil.mapUserToDTO(user);
    }

    public UserDTO buyPremium(Long userId){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        if (!user.getRoles().equals(RoleName.SELLER)){
            throw new RuntimeException("Only Seller can buy Premium");
        }
        user.setProfileType(ProfileType.PREMIUM);
        userRepository.save(user);
        return userUtil.mapUserToDTO(user);
    }


    public UserDTO changeUserRoleOrProfileType(Long userId, RoleName newRole, ProfileType newProfileType){
        User user = userRepository.findById(userId)
                .orElseThrow(()-> new RuntimeException("User not found"));
        if (newProfileType != null) {
            user.setProfileType(newProfileType);
        }
        userRepository.save(user);

        return userUtil.mapUserToDTO(user);
    }
}
