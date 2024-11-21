package com.example.autodealerworld.entity.dto;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.Region;
import com.example.autodealerworld.entity.User;
import com.example.autodealerworld.entity.enums.CarStatus;
import com.example.autodealerworld.entity.enums.Currency;
import lombok.Data;

@Data
public class CarDTO {

    private Long id;

    private Brand brand;

    private Double price;

    private Currency currency;

    private Region region;

    private CarStatus carStatus;

    private User owner;
}
