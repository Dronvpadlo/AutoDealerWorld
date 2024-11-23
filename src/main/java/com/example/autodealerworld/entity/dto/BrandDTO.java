package com.example.autodealerworld.entity.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandDTO {

    private Long brandId;

    @NotNull(message = "Brand can not be null")
    private String name;

    @JsonIgnore
    private List<CarDTO> cars;
}
