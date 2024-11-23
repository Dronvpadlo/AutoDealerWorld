package com.example.autodealerworld.controllers.buyer;

import com.example.autodealerworld.entity.dto.CarDTO;
import com.example.autodealerworld.entity.dto.CarFilterDTO;
import com.example.autodealerworld.services.CarService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/cars/default")
@RequiredArgsConstructor
public class BuyerCarController {
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

    @GetMapping("/filter")
    public ResponseEntity<List<CarDTO>> getFilteredCars(CarFilterDTO carFilterDTO){
        List<CarDTO> cars = carService.getFilteredCar(carFilterDTO);
        return new ResponseEntity<>(cars, HttpStatus.OK);
    }
}
