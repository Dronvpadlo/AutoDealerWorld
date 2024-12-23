package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.enums.RegionCode;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RegionDTO {
    private Long regionId;

    @NotNull(message = "region can not be null")
    private String name;


    private RegionCode code;

    @JsonIgnore
    private List<Long> carIds;
}
