package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.enums.ProfileType;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long userId;

    private String username;

    private String email;

    private String phoneNumber;

    private Set<String> role;

    private ProfileType profileType;

    private LocalDateTime registeredAt;
}
