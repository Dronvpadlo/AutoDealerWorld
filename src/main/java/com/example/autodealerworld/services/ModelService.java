package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.Model;
import com.example.autodealerworld.entity.dto.ModelDTO;
import com.example.autodealerworld.util.ModelUtil;
import com.example.autodealerworld.repository.BrandRepository;
import com.example.autodealerworld.repository.ModelRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ModelService {

    private final ModelRepository modelRepository;

    private final ModelUtil modelUtil;

    private final BrandRepository brandRepository;

    public List<ModelDTO> findAll() {
        return modelRepository.findAll()
                .stream().map(modelUtil::mapModelToDTO)
                .toList();
    }

    public void createModel(ModelDTO modelDTO){
        Brand brand = brandRepository.findById(modelDTO.getBrandId())
                        .orElseThrow(() -> new RuntimeException("Brand not found"));
        Model model = modelUtil.mapModelToEntity(modelDTO, brand);
        modelRepository.save(model);
    }

    public void deleteModelById(Long id){
        modelRepository.deleteById(id);
    }

    public ModelDTO updateModel(Long id, ModelDTO modelDTO){

        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        model.setName(modelDTO.getName());

        Brand brand = brandRepository.findById(modelDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        model.setBrand(brand);

        Model newModel = modelRepository.save(model);
        return modelUtil.mapModelToDTO(newModel);
    }

    public ModelDTO updateModelPartially(Long id, ModelDTO modelDTO){
        Model model = modelRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Model not found"));
        if (modelDTO.getName() != null) {
            model.setName(modelDTO.getName());
        }
        Brand brand = brandRepository.findById(modelDTO.getBrandId())
                .orElseThrow(() -> new RuntimeException("Brand not found"));
        if (modelDTO.getBrandId() != null){
            model.setBrand(brand);
        }
        Model newModel = modelRepository.save(model);
        return modelUtil.mapModelToDTO(newModel);
    }

}
