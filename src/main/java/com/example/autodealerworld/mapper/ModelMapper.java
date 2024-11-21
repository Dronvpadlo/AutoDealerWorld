package com.example.autodealerworld.mapper;

import com.example.autodealerworld.entity.Model;
import com.example.autodealerworld.entity.dto.ModelDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ModelMapper {

    Model mapModelToEntity(ModelDTO modelDTO);

    ModelDTO mapModelToDTO(Model model);
}
