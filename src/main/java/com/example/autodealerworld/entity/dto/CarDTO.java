package com.example.autodealerworld.entity.dto;


import com.example.autodealerworld.entity.enums.CarStatus;
import com.example.autodealerworld.entity.enums.Currency;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CarDTO {

    private Long carId;

    @NotNull(message = "Brand can not be null")
    private BrandDTO brand;

    @NotNull(message = "Model can not be null")
    private ModelDTO model;

    @NotNull(message = "price can not be null")
    @Positive(message = "price must be positive")
    private Double price;

    private Double priceInUSD;

    private Double priceInEUR;

    private Double priceInUAH;

    private String exchangeRateInfo;


    @NotNull(message = "year can not be null")
    @Positive(message = "year must be positive")
    private Long year;

    @NotNull(message = "current can not be null")
    private Currency currency;

    @NotNull(message = "region can not be null")
    private RegionDTO region;

    @NotNull(message = "car status can not be null")
    private CarStatus carStatus;

    private UserDTO owner;
}
