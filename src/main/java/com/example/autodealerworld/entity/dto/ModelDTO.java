package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.Brand;
import lombok.Data;

@Data
public class ModelDTO {
    private Long id;
    private String name;
    private BrandDTO brand;
}
