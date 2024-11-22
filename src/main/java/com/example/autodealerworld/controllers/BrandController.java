package com.example.autodealerworld.controllers;

import com.example.autodealerworld.entity.dto.BrandDTO;
import com.example.autodealerworld.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    @PostMapping("")
    public ResponseEntity<BrandDTO> postCar(@RequestBody BrandDTO brandDTO) {

        return new ResponseEntity<>(brandService.createBrand(brandDTO), HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<List<BrandDTO>> getBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }
}
