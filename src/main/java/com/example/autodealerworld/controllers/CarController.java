package com.example.autodealerworld.controllers;

import com.example.autodealerworld.entity.CarEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;
import java.util.ArrayList;

@RestController
@RequiredArgsConstructor
public class CarController {

    @GetMapping("/cars")
    public ResponseEntity<String> getCars() {
        return new ResponseEntity<>("Koenigsegg", HttpStatus.OK);
    }

    @PostMapping("/cars")
    public ResponseEntity<CarEntity> postCar(@RequestBody CarEntity carEntity) {
        return new ResponseEntity<>(carEntity, HttpStatus.CREATED);
    }
}
