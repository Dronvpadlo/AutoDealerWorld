package com.example.autodealerworld.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SignUpResponseDTO {

    private Long id;

    private String username;

    private LocalDateTime registeredAt;
}
