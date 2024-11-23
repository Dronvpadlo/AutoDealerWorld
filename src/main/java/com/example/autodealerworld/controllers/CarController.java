package com.example.autodealerworld.controllers;

import com.example.autodealerworld.entity.dto.CarDTO;
import com.example.autodealerworld.services.CarService;
import jakarta.validation.Valid;
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
    public ResponseEntity<CarDTO> postCar(@RequestBody @Valid CarDTO carDTO){
        return new ResponseEntity<>(carService.createCar(carDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCar(@PathVariable Long id){
        carService.deleteCarById(id);
        return new ResponseEntity<>("Car deleted", HttpStatus.ACCEPTED);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CarDTO> patchCar(@PathVariable Long id, @RequestBody CarDTO carDTO){
        return new ResponseEntity<>(carService.updateCarPartially(id, carDTO), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarDTO> putCar(@PathVariable Long id, @RequestBody CarDTO carDTO){
        return new ResponseEntity<>(carService.updateCar(id, carDTO), HttpStatus.OK);
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
    }*/

}
