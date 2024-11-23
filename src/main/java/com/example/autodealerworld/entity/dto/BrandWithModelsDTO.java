package com.example.autodealerworld.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandWithModelsDTO {

    private Long brandId;

    @NotNull(message = "Brand can not be null")
    private String name;

    private List<ModelDTO> models;

    @JsonIgnore
    private List<CarDTO> cars;
}
