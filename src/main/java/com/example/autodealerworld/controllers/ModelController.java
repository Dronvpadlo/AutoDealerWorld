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
    @PostMapping("")
    public ResponseEntity<ModelDTO> postModel(@RequestBody ModelDTO modelDTO) {
        modelService.createModel(modelDTO);
        return new ResponseEntity<>(modelDTO, HttpStatus.CREATED);
    }
    @GetMapping("")
    public ResponseEntity<List<ModelDTO>> getModels() {
        return new ResponseEntity<>(modelService.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteModelById(@PathVariable Long id){
        modelService.deleteModelById(id);
        return new ResponseEntity<>("Model deleted", HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModelDTO> putModel(@PathVariable Long id, @RequestBody ModelDTO modelDTO){
        return new ResponseEntity<>(modelService.updateModel(id, modelDTO), HttpStatus.OK);

    }

    @PatchMapping("/{id}")
    public ResponseEntity<ModelDTO> patchModel(@PathVariable Long id, @RequestBody ModelDTO modelDTO){
        return new ResponseEntity<>(modelService.updateModelPartially(id, modelDTO), HttpStatus.OK);
    }
}
