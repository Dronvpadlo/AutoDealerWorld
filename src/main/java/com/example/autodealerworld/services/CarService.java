package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.dto.CarDTO;
import com.example.autodealerworld.repository.CarRepository;
import com.example.autodealerworld.util.CarUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarService {

    private final CarRepository carRepository;

    private final CarUtil carUtil;

    public List<CarDTO> findAll(){
        return carRepository.findAll()
                .stream().map(carUtil::mapCarToDTO)
                .toList();
    }

    public CarDTO createCar(CarDTO carDTO){
        System.out.println("DTO: " + carDTO);
        Car car = carUtil.mapCarToEntity(carDTO);
        carRepository.save(car);
        return carDTO;
    }
}
