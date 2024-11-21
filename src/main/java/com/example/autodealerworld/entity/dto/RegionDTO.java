package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.enums.RegionCode;
import lombok.Data;

@Data
public class RegionDTO {
    private Long id;

    private String name;

    private RegionCode code;
}
