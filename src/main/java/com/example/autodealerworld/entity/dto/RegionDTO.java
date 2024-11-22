package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.Car;

import com.example.autodealerworld.entity.enums.RegionCode;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RegionDTO {
    private Long regionId;

    private String name;

    private RegionCode code;

    private List<Car> cars;
}
