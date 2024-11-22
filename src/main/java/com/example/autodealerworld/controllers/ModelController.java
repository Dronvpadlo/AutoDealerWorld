package com.example.autodealerworld.controllers;

import com.example.autodealerworld.entity.dto.ModelDTO;
import com.example.autodealerworld.services.ModelService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars/model")
@RequiredArgsConstructor
public class ModelController {

    private final ModelService modelService;
    /*@PostMapping("")
    public ResponseEntity<ModelDTO> postModel(@RequestBody ModelDTO modelDTO) {
        return new ResponseEntity<>(modelService.createModel(modelDTO), HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<List<ModelDTO>> getModels() {
        return new ResponseEntity<>(modelService.findAll(), HttpStatus.OK);
    }*/
}
