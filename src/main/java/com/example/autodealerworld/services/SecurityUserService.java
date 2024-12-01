package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Role;
import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.dto.SignUpRequestDTO;
import com.example.autodealerworld.entity.dto.UserDTO;
import com.example.autodealerworld.entity.enums.ProfileType;
import com.example.autodealerworld.repository.RoleRepository;
import com.example.autodealerworld.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    public UserDTO createUser(SignUpRequestDTO signUpRequestDTO) {
        User user = new User();
        user.setUsername(signUpRequestDTO.getUsername());
        user.setPassword(passwordEncoder.encode(signUpRequestDTO.getPassword()));
        user.setEmail(signUpRequestDTO.getEmail());
        user.setPhoneNumber(signUpRequestDTO.getPhoneNumber());
        user.setProfileType(ProfileType.BASIC);
        Set<Role> roles = roleRepository.findByNameIn(signUpRequestDTO.getRoles());
        if (roles.isEmpty()) {
            throw new IllegalArgumentException("One or more roles not found");
        }
        user.setRoles(roles);

        userRepository.save(user);

        return mapToUserDTO(user);
    }

    private UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setUserId(user.getId());
        userDTO.setUsername(user.getUsername());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setRole(
                user.getRoles()
                        .stream()
                        .map(Role::getName)
                        .collect(Collectors.toSet())
        );
        userDTO.setProfileType(user.getProfileType());
        return userDTO;
    }

}
