package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.enums.RegionCode;
import lombok.Data;

import java.util.List;

@Data
public class RegionDTO {
    private Long id;

    private String name;

    private RegionCode code;

    private List<Car> cars;
}
