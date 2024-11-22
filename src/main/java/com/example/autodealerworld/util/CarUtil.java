package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.Model;
import com.example.autodealerworld.entity.Region;
import com.example.autodealerworld.entity.dto.*;
import com.example.autodealerworld.services.BrandService;
import com.example.autodealerworld.services.ModelService;
import com.example.autodealerworld.services.RegionService;
import org.springframework.stereotype.Component;

@Component
public class CarUtil {

    private final BrandUtil brandUtil;

    private final BrandService brandService;

    private final RegionService regionService;

    private final RegionUtil regionUtil;

    private final ModelUtil modelUtil;

    private final ModelService modelService;

    public CarUtil(BrandUtil brandUtil, BrandService brandService, RegionService regionService, RegionUtil regionUtil, ModelUtil modelUtil, ModelService modelService) {
        this.brandUtil = brandUtil;
        this.brandService = brandService;
        this.regionService = regionService;
        this.regionUtil = regionUtil;
        this.modelUtil = modelUtil;
        this.modelService = modelService;
    }

    public Car mapCarToEntity(CarDTO carDTO){
        Car car = new Car();
        car.setPrice(carDTO.getPrice());
        car.setCarStatus(carDTO.getCarStatus());
        car.setPrice(carDTO.getPrice());
        car.setYear(carDTO.getYear());
        car.setCurrency(carDTO.getCurrency());
        if (carDTO.getBrand() != null){
            Brand brand = brandService.findBrandById(carDTO.getBrand().getBrandId());
            car.setBrand(brand);

        }

        if (carDTO.getRegion() != null){
            Region region = regionService.findRegionById(carDTO.getRegion().getRegionId());
            car.setRegion(region);

        }
        if (carDTO.getModel() != null){
            Model model = modelService.findModelById(carDTO.getModel().getModelId());
            car.setModel(model);
        }
        return car;
    }

    public CarDTO mapCarToDTO(Car car){
        CarDTO carDTO = new CarDTO();
        carDTO.setPrice(car.getPrice());
        carDTO.setCarStatus(car.getCarStatus());
        carDTO.setPrice(car.getPrice());
        carDTO.setYear(car.getYear());
        carDTO.setCurrency(car.getCurrency());
        carDTO.setCarId(car.getId());
        if (car.getBrand() != null){
            Brand brand = brandService.findBrandById(car.getBrand().getId());
            BrandDTO brandDTO = brandUtil.mapBrandToDTO(brand);
            carDTO.setBrand(brandDTO);
        }
        if (car.getRegion() != null){
            Region region = regionService.findRegionById(car.getRegion().getId());
            RegionDTO regionDTO = regionUtil.MapRegionToDTO(region);
            carDTO.setRegion(regionDTO);
        }

        if (car.getModel() != null){
            Model model = modelService.findModelById(car.getModel().getId());
            ModelDTO modelDTO = modelUtil.mapModelToDTO(model);
            carDTO.setModel(modelDTO);
        }
        return carDTO;
    }
}
