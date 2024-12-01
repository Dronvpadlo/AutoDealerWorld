package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SignUpRequestDTO {
    private String username;
    private String password;
    private String email;
    private String phoneNumber;
    private Set<String> roles;
}
