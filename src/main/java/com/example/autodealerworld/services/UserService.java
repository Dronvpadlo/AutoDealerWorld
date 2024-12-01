package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.dto.RegisterDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.entity.enums.ProfileType;
import com.example.autodealerworld.repository.RoleRepository;
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
    private final RoleRepository roleRepository;

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

    public UserDTO beAsSeller(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (user.getEmail() == null || user.getPhoneNumber() == null) {
            throw new RuntimeException("Email and phone number are required to become a seller");
        }

        Role sellerRole = roleRepository.findByName("SELLER")
                .orElseThrow(() -> new RuntimeException("Role SELLER not found"));

        user.getRoles().add(sellerRole);

        userRepository.save(user);
        return userUtil.mapUserToDTO(user);
    }

    public UserDTO buyPremium(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        boolean isSeller = user.getRoles().stream()
                .anyMatch(role -> role.getName().equals("SELLER"));

        if (!isSeller) {
            throw new RuntimeException("Only Seller can buy Premium");
        }
        user.setProfileType(ProfileType.PREMIUM);
        userRepository.save(user);

        return userUtil.mapUserToDTO(user);
    }
}
