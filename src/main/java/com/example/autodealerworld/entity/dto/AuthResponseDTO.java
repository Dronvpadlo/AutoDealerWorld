package com.example.autodealerworld.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
public class AuthResponseDTO {

    private String accessToken;

    private String refreshToken;
}
