package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Region;
import com.example.autodealerworld.entity.dto.RegionDTO;
import org.springframework.stereotype.Component;

@Component
public class RegionUtil {

    public Region mapRegionToEntity(RegionDTO regionDTO){
        Region region = new Region();
        region.setName(regionDTO.getName());
        region.setCode(regionDTO.getCode());
        return region;
    }

    public RegionDTO MapRegionToDTO(Region region){
        RegionDTO regionDTO = new RegionDTO();
        regionDTO.setName(region.getName());
        regionDTO.setRegionId(region.getId());
        regionDTO.setCode(region.getCode());
        return regionDTO;
    }
}
