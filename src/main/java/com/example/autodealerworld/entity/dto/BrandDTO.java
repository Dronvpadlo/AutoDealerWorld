package com.example.autodealerworld.entity.dto;

import lombok.Data;

import java.util.List;

@Data
public class BrandDTO {

    private Long id;

    private String name;

    private ModelDTO model;

    private List<CarDTO> cars;
}
