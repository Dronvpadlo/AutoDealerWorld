package com.example.autodealerworld.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModelDTO {
    private Long modelId;

    @NotNull(message = "Model can not be null")
    private String name;

    @NotNull(message = "Brand can not be null")
    private Long brandId;
}
