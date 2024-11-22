package com.example.autodealerworld.entity.dto;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandWithModelsDTO {

    private Long brandId;

    private String name;

    private List<ModelDTO> models;

    @JsonIgnore
    private List<CarDTO> cars;
}
