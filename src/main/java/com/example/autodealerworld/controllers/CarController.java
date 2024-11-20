package com.example.autodealerworld.controllers;

import com.example.autodealerworld.entity.Car;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars")
@RequiredArgsConstructor
public class CarController {

    @GetMapping("")
    public ResponseEntity<List<Car>> getCars() {
        return null;
    }

    @PostMapping("")
    public ResponseEntity<Car> postCar(@RequestBody Car carEntity) {
        return new ResponseEntity<>(carEntity, HttpStatus.CREATED);
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
