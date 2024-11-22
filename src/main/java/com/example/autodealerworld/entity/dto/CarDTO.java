package com.example.autodealerworld.entity.dto;


import com.example.autodealerworld.entity.enums.CarStatus;
import com.example.autodealerworld.entity.enums.Currency;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CarDTO {

    private Long carId;

    private BrandDTO brand;

    private Double price;

    private Currency currency;

    private RegionDTO region;

    private CarStatus carStatus;

    private UserDTO owner;
}
