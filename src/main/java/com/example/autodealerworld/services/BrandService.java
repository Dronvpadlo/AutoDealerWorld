package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.dto.BrandWithModelsDTO;
import com.example.autodealerworld.repository.BrandRepository;
import com.example.autodealerworld.util.BrandUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BrandService {

    private final BrandRepository brandRepository;

    private final BrandUtil brandUtil;

    public List<BrandWithModelsDTO> findAll() {
        return brandRepository.findAll()
                .stream().map(brandUtil::mapBrandWithModelsToDTO)
                .toList();
    }

    public Brand findBrandById(Long id){
        return brandRepository.findById(id).orElseThrow(() -> new RuntimeException("Brand not found with id: " + id));

    }

    public BrandWithModelsDTO createBrand(BrandWithModelsDTO brandDTO){
        Brand brand = brandUtil.mapBrandToEntity(brandDTO);
        brandRepository.save(brand);
        return brandDTO;
    }

    public void deleteBrandById(Long id){
        brandRepository.deleteById(id);
    }

    public BrandWithModelsDTO updateBrand(Long id, BrandWithModelsDTO brandDTO){
        Brand brand = brandRepository.findById(id).orElseThrow(()-> new RuntimeException("Brand not found"));
        Brand newBrand = brandUtil.mapBrandToEntity(brandDTO);
        brand.setName(newBrand.getName());
        brand.setModels(newBrand.getModels());
        brandRepository.save(brand);
        return brandUtil.mapBrandWithModelsToDTO(newBrand);
    }

    public BrandWithModelsDTO updateBrandPartially(Long id, BrandWithModelsDTO brandDTO){
        Brand brand = brandRepository.findById(id).orElseThrow(()-> new RuntimeException("Brand not found"));
        Brand newBrand = brandUtil.mapBrandToEntity(brandDTO);
        if (newBrand.getName() != null) {
            brand.setName(newBrand.getName());
        }
        if (newBrand.getModels() != null){
            brand.setModels(newBrand.getModels());
        }

        brandRepository.save(brand);
        return brandUtil.mapBrandWithModelsToDTO(newBrand);

    }

}
