package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.NewUser;
import com.example.autodealerworld.entity.dto.SignUpRequestDTO;
import com.example.autodealerworld.entity.dto.SignUpResponseDTO;
import com.example.autodealerworld.repository.NewUserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class SecurityUserService implements UserDetailsService {

    private final NewUserRepository newUserRepository;

    private final PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return newUserRepository
                .findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("User not found"));
    }

    public SignUpResponseDTO createUser(@Valid SignUpRequestDTO signUpRequestDTO){
        String password = passwordEncoder.encode(signUpRequestDTO.getPassword());

        NewUser newUser = new NewUser();
        newUser.setUsername(signUpRequestDTO.getUsername());
        newUser.setPassword(password);
        newUser.setRole(signUpRequestDTO.getRole());
        newUser.setRegisteredAt(LocalDateTime.now());
        newUserRepository.save(newUser);

        return SignUpResponseDTO.builder()
                .id(newUser.getId())
                .username(newUser.getUsername())
                .registeredAt(newUser.getRegisteredAt())
                .build();
    }




}
