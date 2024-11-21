package com.example.autodealerworld.mapper;

import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CarMapper {

    CarDTO mapCarToDTO(Car car);

    Car mapCarToEntity(CarDTO carDTO);
}
