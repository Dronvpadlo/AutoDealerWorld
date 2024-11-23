package com.example.autodealerworld.controllers.manager;

import com.example.autodealerworld.entity.dto.BrandWithModelsDTO;
import com.example.autodealerworld.services.BrandService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars/manager/brand")
@RequiredArgsConstructor
public class BrandController {

    private final BrandService brandService;
    @PostMapping("")
    public ResponseEntity<BrandWithModelsDTO> postBrand(@RequestBody BrandWithModelsDTO brandDTO) {
        return new ResponseEntity<>(brandService.createBrand(brandDTO), HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<List<BrandWithModelsDTO>> getBrands() {
        return new ResponseEntity<>(brandService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBrand(@PathVariable Long id){
        brandService.deleteBrandById(id);
        return new ResponseEntity<>("Brand deleted", HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BrandWithModelsDTO> putBrand(@PathVariable Long id, @RequestBody BrandWithModelsDTO brandDTO){
        return new ResponseEntity<>(brandService.updateBrand(id, brandDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BrandWithModelsDTO> patchBrand(@PathVariable Long id, @RequestBody BrandWithModelsDTO brandDTO){
        return new ResponseEntity<>(brandService.updateBrandPartially(id, brandDTO), HttpStatus.OK);
    }
}
