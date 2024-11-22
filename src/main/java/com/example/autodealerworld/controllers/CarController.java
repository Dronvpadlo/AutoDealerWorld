package com.example.autodealerworld.controllers;

import com.example.autodealerworld.entity.dto.CarDTO;
import com.example.autodealerworld.repository.BrandRepository;
import com.example.autodealerworld.repository.CarRepository;
import com.example.autodealerworld.repository.RegionRepository;
import com.example.autodealerworld.services.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarService carService;

    @GetMapping("")
    public ResponseEntity<List<CarDTO>> getCars(){
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<CarDTO> postCar(@RequestBody CarDTO carDTO){
        return new ResponseEntity<>(carService.createCar(carDTO), HttpStatus.CREATED);
    }




    /*@GetMapping("/filter/price")
    public ResponseEntity<List<Car>> getCarBetweenPrice(
            @RequestParam (name = "minPrice", required = false) Double minPrice,
            @RequestParam (name = "maxPrice", required = false) Double maxPrice
    )
    {
        if (minPrice != null && maxPrice != null){
            return ResponseEntity.ok(carRepository.findCarsByPriceBetween(minPrice, maxPrice));
        }else if (minPrice != null){
            return ResponseEntity.ok(carRepository.findCarsByPriceGreaterThan(minPrice));
        }else if (maxPrice != null){
            return ResponseEntity.ok(carRepository.findCarsByPriceLessThan(maxPrice));
        }else {
            return ResponseEntity.ok(carRepository.findAll());
        }
    }

    @GetMapping("/region")
    public ResponseEntity<List<Car>> getCarByRegionAndBrand(
            @RequestParam (name = "region") String regionName,
            @RequestParam (name = "brand") String brandName
    ){
        if (regionName != null && brandName != null){
            return ResponseEntity.of(carRepository.findCarsByRegionAndBrand(regionName, brandName));
        }else if (regionName != null){
            return ResponseEntity.of(carRepository.findCarsByRegion(regionName));
        }else if (brandName != null){
            return ResponseEntity.of(carRepository.findCarsByBrand(brandName));
        }else {
            return ResponseEntity.ok(carRepository.findAll());
        }
    }*/
    /*@PostMapping("")
    public ResponseEntity<Car> postCar(@RequestBody Car carEntity) {
        carRepository.save(carEntity);
        return new ResponseEntity<>(carEntity, HttpStatus.CREATED);
    }

    /*@PostMapping("/region")
    public ResponseEntity<Region> postRegion(@RequestBody Region region){
        regionRepository.save(region);
        return new ResponseEntity<>(region, HttpStatus.CREATED);
    }

    /*@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        carRepository.deleteById(id);
        return new ResponseEntity<>("Car was deleted", HttpStatus.NO_CONTENT);
    }

    /*@PutMapping("/{id}")
    public ResponseEntity<Car> patchCar(@PathVariable Long id, @RequestBody Car car){
        return ResponseEntity.of(
                carRepository.findById(id)
                        .map(oldCar ->{
                            oldCar.setCarStatus(car.getCarStatus());
                            oldCar.setBrand(car.getBrand());
                            oldCar.setPrice(car.getPrice());
                            oldCar.setOwner(car.getOwner());
                            oldCar.setRegion(car.getRegion());
                            return carRepository.save(oldCar);
                        })
        );
    }*/

}
