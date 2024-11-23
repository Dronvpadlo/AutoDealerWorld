package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.enums.CarStatus;
import lombok.Data;

@Data
public class CarFilterDTO {

    private String brand;

    private String model;

    private String region;

    private Double minPrice;

    private Double maxPrice;

    private Long minYear;

    private Long maxYear;

    private CarStatus carStatus;
}
