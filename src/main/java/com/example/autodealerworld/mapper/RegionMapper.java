package com.example.autodealerworld.mapper;

import com.example.autodealerworld.entity.Region;
import com.example.autodealerworld.entity.dto.RegionDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RegionMapper {
    Region mapReginToDTO(RegionDTO regionDTO);

    RegionDTO mapRegionToEntity(Region region);
}
