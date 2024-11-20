package com.example.autodealerworld.controllers;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.Model;
import com.example.autodealerworld.repository.BrandRepository;
import com.example.autodealerworld.repository.CarRepository;
import com.example.autodealerworld.repository.ModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    private final CarRepository carRepository;

    private final ModelRepository modelRepository;

    private final BrandRepository brandRepository;

    @GetMapping("")
    public ResponseEntity<List<Car>> getCars() {
        List<Car> allCars = carRepository.findAll();
        return new ResponseEntity<>(allCars, HttpStatus.OK);
    }

    @GetMapping("")
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

    @PostMapping("")
    public ResponseEntity<Car> postCar(@RequestBody Car carEntity) {
        carRepository.save(carEntity);
        return new ResponseEntity<>(carEntity, HttpStatus.CREATED);
    }
    @PostMapping("/model")
    public ResponseEntity<Model> postModel(@RequestBody Model model) {
        modelRepository.save(model);
        return new ResponseEntity<>(model, HttpStatus.CREATED);
    }
    @PostMapping("/brand")
    public ResponseEntity<Brand> postCar(@RequestBody Brand brand) {
        brandRepository.save(brand);
        return new ResponseEntity<>(brand, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        return new ResponseEntity<>("advertisement was deleted", HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Car> patchCar(@PathVariable Long id){
        return null;
    }

}
