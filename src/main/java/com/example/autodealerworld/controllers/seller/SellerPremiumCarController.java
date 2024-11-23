package com.example.autodealerworld.controllers.seller;

import com.example.autodealerworld.entity.dto.CarDTO;
import com.example.autodealerworld.entity.dto.CarFilterDTO;
import com.example.autodealerworld.entity.enums.RegionCode;
import com.example.autodealerworld.services.CarService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/cars/premiumseller")
@RequiredArgsConstructor
public class SellerPremiumCarController {
    private final CarService carService;

    @GetMapping("")
    public ResponseEntity<List<CarDTO>> getCars(){
        return new ResponseEntity<>(carService.findAll(), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<CarDTO> getCarById(@PathVariable Long id, HttpServletRequest request){
        String viewerIp = request.getRemoteAddr();
        CarDTO carDTO = carService.findCarById(id, viewerIp);
        return new ResponseEntity<>(carDTO, HttpStatus.OK);

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

    @GetMapping("/filter")
    public ResponseEntity<List<CarDTO>> getFilteredCars(CarFilterDTO carFilterDTO){
        List<CarDTO> cars = carService.getFilteredCar(carFilterDTO);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }

    @GetMapping("/stats/average-price")
    public ResponseEntity<Double> getAveragePriceByBrand(
            @RequestParam(required = false) String brand,
            @RequestParam(required = false) String model,
            @RequestParam(required = false) String region,
            @RequestParam(required = false) Long year,
            @RequestParam(required = false) RegionCode regionCode

    ){
        Double averagePrice = carService.getAveragePrice(brand, model, region, year, regionCode);
        return new ResponseEntity<>(averagePrice, HttpStatus.OK);
    }

    @GetMapping("/stats/views")
    public ResponseEntity<Long> getCarViews(@RequestParam Long carId) {
        Long views = carService.getCarViews(carId);
        return new ResponseEntity<>(views, HttpStatus.OK);
    }

}
