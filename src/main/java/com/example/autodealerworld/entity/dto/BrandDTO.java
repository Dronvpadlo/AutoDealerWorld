package com.example.autodealerworld.entity.dto;


import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private Long brandId;

    private String name;

    private List<ModelDTO> models;

    private List<CarDTO> cars;
}
