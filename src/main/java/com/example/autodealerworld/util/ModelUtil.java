package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.Model;
import com.example.autodealerworld.entity.dto.ModelDTO;
import org.springframework.stereotype.Component;

@Component
public class ModelUtil {

    public Model mapModelToEntity(ModelDTO modelDTO, Brand brand) {
        Model model = new Model();
        model.setName(modelDTO.getName());
        model.setBrand(brand);
        return model;
    }

    public ModelDTO mapModelToDTO(Model model) {
        ModelDTO modelDTO = new ModelDTO();
        modelDTO.setModelId(model.getId());
        modelDTO.setBrandId(model.getBrand().getId());
        modelDTO.setName(model.getName());
        return modelDTO;
    }


}
