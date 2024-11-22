package com.example.autodealerworld.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private Long userId;

    private String username;

    private String email;

    private String phoneNumber;
}
