package com.example.autodealerworld.mapper;

import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.dto.CarDTO;
import org.mapstruct.Mapper;

@Mapper
public interface CarMapper {

    Car mapCarToDTO(CarDTO carDTO);

    CarDTO mapCarToEntity(Car car);
}
