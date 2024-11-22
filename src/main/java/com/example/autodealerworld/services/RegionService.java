package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.Region;
import com.example.autodealerworld.entity.dto.RegionDTO;
import com.example.autodealerworld.repository.CarRepository;
import com.example.autodealerworld.repository.RegionRepository;
import com.example.autodealerworld.util.RegionUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RegionService {

    private final RegionRepository regionRepository;

    private final RegionUtil regionUtil;

    private final CarRepository carRepository;

    public List<RegionDTO> findAll(){
        return regionRepository.findAll()
                .stream()
                .map(regionUtil::MapRegionToDTO).toList();
    }

    public Region findRegionById(Long regionId){
        return regionRepository.findById(regionId).orElseThrow(() -> new RuntimeException("Region not found with id: " + regionId));
    }

    public RegionDTO createRegion(RegionDTO regionDTO){
        Region region = regionUtil.mapRegionToEntity(regionDTO);
        regionRepository.save(region);
        return regionDTO;
    }

    public void deleteRegionById(Long id){
        regionRepository.deleteById(id);
    }

    public RegionDTO updateRegion(Long id, RegionDTO regionDTO){
        Region region = regionRepository.findById(id).orElseThrow(()->new RuntimeException("Region not found"));
        region.setName(regionDTO.getName());
        region.setCode(regionDTO.getCode());

        if (regionDTO.getCarIds() != null) {
            List<Car> updatedCars = carRepository.findAllById(regionDTO.getCarIds());
            region.getCars().clear();
            region.getCars().addAll(updatedCars);
        }

        Region newRegion = regionRepository.save(region);
        return regionUtil.MapRegionToDTO(newRegion);
    }
    public RegionDTO updateRegionPartially(Long id, RegionDTO regionDTO){
        Region region = regionRepository.findById(id).orElseThrow(()->new RuntimeException("Region not found"));
        if (regionDTO.getName() != null) {
            region.setName(regionDTO.getName());
        }
        if (regionDTO.getCode() != null) {
            region.setCode(regionDTO.getCode());
        }

        if (regionDTO.getCarIds() != null){
            List<Car> cars = carRepository.findAllById(regionDTO.getCarIds());
            region.setCars(cars);
        }


        Region newRegion = regionRepository.save(region);
        return regionUtil.MapRegionToDTO(newRegion);
    }
}