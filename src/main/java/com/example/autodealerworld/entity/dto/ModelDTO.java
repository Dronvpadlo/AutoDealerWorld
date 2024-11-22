package com.example.autodealerworld.entity.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModelDTO {
    private Long modelId;
    private String name;
    private Long brandId;
}
