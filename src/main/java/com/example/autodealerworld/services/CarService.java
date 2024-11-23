package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.dto.CarDTO;
import com.example.autodealerworld.entity.dto.CarFilterDTO;
import com.example.autodealerworld.repository.BrandRepository;
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

    private final BrandRepository brandRepository;



    public List<CarDTO> findAll(){
        return carRepository.findAll()
                .stream().map(carUtil::mapCarToDTO)
                .toList();
    }

    public CarDTO createCar(CarDTO carDTO){
        Brand brand = brandRepository.findById(carDTO.getBrand().getBrandId())
                .orElseThrow(()-> new RuntimeException("Brand not found"));

        boolean modelBelongsToBrand = brand.getModels()
                .stream()
                .anyMatch(model -> model.getId().equals(carDTO.getModel().getModelId()));
        if (!modelBelongsToBrand){
            throw new RuntimeException("Model does not belong to request brand");
        }

        Car car = carUtil.mapCarToEntity(carDTO);
        carRepository.save(car);
        return carDTO;
    }

    public void deleteCarById(Long id){
        carRepository.deleteById(id);
    }

    public CarDTO updateCar(Long id, CarDTO carDTO){
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        Car newCar = carUtil.mapCarToEntity(carDTO);
        car.setYear(newCar.getYear());
        car.setPrice(newCar.getPrice());
        car.setCurrency(newCar.getCurrency());
        car.setCarStatus(newCar.getCarStatus());
        car.setBrand(newCar.getBrand());
        car.setModel(newCar.getModel());
        car.setRegion(newCar.getRegion());
        carRepository.save(car);
        return carUtil.mapCarToDTO(car);

    }

    public CarDTO updateCarPartially(Long id, CarDTO carDTO){
        Car car = carRepository.findById(id).orElseThrow(() -> new RuntimeException("Car not found"));
        Car newCar = carUtil.mapCarToEntity(carDTO);

        if (newCar.getYear() != null) {
            car.setYear(newCar.getYear());
        }
        if (newCar.getPrice() != null) {
            car.setPrice(newCar.getPrice());
        }
        if (newCar.getCurrency() != null) {
            car.setCurrency(newCar.getCurrency());
        }
        if (newCar.getCarStatus() != null) {
            car.setCarStatus(newCar.getCarStatus());
        }
        if (newCar.getBrand() != null) {
            car.setBrand(newCar.getBrand());
        }
        if (newCar.getModel() != null) {
            car.setModel(newCar.getModel());
        }
        if (newCar.getRegion() != null) {
            car.setRegion(newCar.getRegion());
        }

        carRepository.save(car);
        return carUtil.mapCarToDTO(car);

    }

    public List<CarDTO> getFilteredCar(CarFilterDTO filterDTO){
        List<Car> cars = carRepository.findCarsByFilters(
                filterDTO.getBrand(),
                filterDTO.getModel(),
                filterDTO.getRegion(),
                filterDTO.getMinPrice(),
                filterDTO.getMaxPrice(),
                filterDTO.getMinYear(),
                filterDTO.getMaxYear(),
                filterDTO.getCarStatus()
        );
        return cars.stream()
                .map(carUtil::mapCarToDTO).toList();

    }

}
