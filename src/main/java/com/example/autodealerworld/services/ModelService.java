package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.dto.ModelDTO;
import com.example.autodealerworld.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;

    /*public List<ModelDTO> findAll() {
        return modelRepository.findAll()
                .stream().map(modelMapper::mapModelToDTO)
                .toList();
    }

    public ModelDTO createModel(ModelDTO modelDTO){
        modelRepository.save(modelMapper.mapModelToEntity(modelDTO));
        return modelDTO;
    }*/

}
