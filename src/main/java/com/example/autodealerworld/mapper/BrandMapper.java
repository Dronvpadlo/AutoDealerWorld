package com.example.autodealerworld.mapper;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.dto.BrandDTO;
import org.mapstruct.Mapper;

@Mapper
public interface BrandMapper {

    Brand mapBrandToDTO(BrandDTO brandDTO);

    BrandDTO mapBrandToEntity(Brand brand);
}
