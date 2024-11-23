package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.enums.ProfileType;
import com.example.autodealerworld.entity.enums.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long userId;

    private String username;

    private String email;

    private String phoneNumber;

    private UserRole role;

    private ProfileType profileType;
}
