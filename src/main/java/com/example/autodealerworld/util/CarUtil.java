package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.Region;
import com.example.autodealerworld.entity.dto.BrandDTO;
import com.example.autodealerworld.entity.dto.CarDTO;
import com.example.autodealerworld.entity.dto.RegionDTO;
import org.springframework.stereotype.Component;

@Component
public class CarUtil {

    private final BrandUtil brandUtil;

    private final RegionUtil regionUtil;

    public CarUtil(BrandUtil brandUtil, RegionUtil regionUtil) {
        this.brandUtil = brandUtil;
        this.regionUtil = regionUtil;
    }

    public Car mapCarToEntity(CarDTO carDTO){
        Car car = new Car();
        car.setPrice(carDTO.getPrice());
        car.setCarStatus(carDTO.getCarStatus());
        car.setPrice(carDTO.getPrice());
        car.setCurrency(carDTO.getCurrency());
        if (carDTO.getBrand() != null){
            Brand brand = brandUtil.mapBrandToEntity(carDTO.getBrand());
            car.setBrand(brand);

        }

        if (carDTO.getRegion() != null){
            Region region = regionUtil.mapRegionToEntity(carDTO.getRegion());
            car.setRegion(region);

        }
        return car;
    }

    public CarDTO mapCarToDTO(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.setPrice(car.getPrice());
        carDTO.setCarStatus(car.getCarStatus());
        carDTO.setPrice(car.getPrice());
        carDTO.setCurrency(car.getCurrency());
        carDTO.setCarId(car.getId());
        if (car.getBrand() != null){
            BrandDTO brandDTO = brandUtil.mapBrandToDTO(car.getBrand());
            carDTO.setBrand(brandDTO);
        }
        if (car.getRegion() != null){
            RegionDTO regionDTO = regionUtil.MapRegionToDTO(car.getRegion());
            carDTO.setRegion(regionDTO);
        }
        return carDTO;
    }
}
