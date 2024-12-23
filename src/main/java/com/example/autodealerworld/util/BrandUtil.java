package com.example.autodealerworld.util;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.Model;
import com.example.autodealerworld.entity.dto.BrandWithModelsDTO;
import com.example.autodealerworld.entity.dto.BrandDTO;
import com.example.autodealerworld.entity.dto.ModelDTO;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class BrandUtil {

    public Brand mapBrandToEntity(BrandWithModelsDTO brandDTO) {
        Brand brand = new Brand();
        brand.setName(brandDTO.getName());
        if (brandDTO.getModels() != null) {
            List<Model> models = brandDTO.getModels()
                    .stream()
                    .map(modelDTO -> {
                        Model model = new Model();
                        model.setName(modelDTO.getName());
                        model.setBrand(brand);
                        return model;
                    })
                    .toList();
            brand.setModels(models);
        }
        return brand;
    }

    public BrandWithModelsDTO mapBrandWithModelsToDTO(Brand brand){
        BrandWithModelsDTO brandDTO = new BrandWithModelsDTO();
        brandDTO.setName(brand.getName());
        brandDTO.setBrandId(brand.getId());
        if (brand.getModels() != null){
            List<ModelDTO> modelDTOList = brand.getModels()
                    .stream()
                    .map(model -> {
                        ModelDTO modelDTO = new ModelDTO();
                        modelDTO.setName(model.getName());
                        modelDTO.setModelId(model.getId());
                        modelDTO.setBrandId(brand.getId());
                        return modelDTO;
                    })
                    .toList();
            brandDTO.setModels(modelDTOList);
        }
        return brandDTO;
    }
    public BrandDTO mapBrandToDTO(Brand brand){
        BrandDTO brandDTO = new BrandDTO();
        brandDTO.setName(brand.getName());
        brandDTO.setBrandId(brand.getId());
        return brandDTO;
    }
}