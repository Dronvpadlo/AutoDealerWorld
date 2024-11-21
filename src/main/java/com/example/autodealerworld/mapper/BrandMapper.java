package com.example.autodealerworld.mapper;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.dto.BrandDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BrandMapper {

    Brand mapBrandToDTO(BrandDTO brandDTO);

    BrandDTO mapBrandToEntity(Brand brand);
}
