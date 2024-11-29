package com.example.autodealerworld.controllers.car;

import com.example.autodealerworld.entity.dto.RegionDTO;
import com.example.autodealerworld.services.RegionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cars/manager/region")
@RequiredArgsConstructor
public class RegionController {

    private final RegionService regionService;

    @GetMapping("")
    public ResponseEntity<List<RegionDTO>> getRegions(){
        return new ResponseEntity<>(regionService.findAll(), HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<RegionDTO> postRegion(@RequestBody RegionDTO regionDTO){
        return new ResponseEntity<>(regionService.createRegion(regionDTO), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRegion(@PathVariable Long id){
        regionService.deleteRegionById(id);
        return new ResponseEntity<>("Region deleted", HttpStatus.ACCEPTED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> putRegion(@PathVariable Long id, @RequestBody RegionDTO regionDTO){
        return new ResponseEntity<>(regionService.updateRegion(id, regionDTO), HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<RegionDTO> patchRegion(@PathVariable Long id, @RequestBody RegionDTO regionDTO){
        return new ResponseEntity<>(regionService.updateRegionPartially(id, regionDTO), HttpStatus.OK);
    }
}
