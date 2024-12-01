package com.example.autodealerworld.entity.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuthResponseDTO {

    private String accessToken;

    private String refreshToken;
}
