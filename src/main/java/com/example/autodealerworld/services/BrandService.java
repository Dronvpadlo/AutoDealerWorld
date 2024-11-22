package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.dto.BrandDTO;
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

    public List<BrandDTO> findAll() {
        return brandRepository.findAll()
                .stream().map(brandUtil::mapBrandToDTO)
                .toList();
    }

    public BrandDTO createBrand(BrandDTO brandDTO){
        Brand brand = brandUtil.mapBrandToEntity(brandDTO);
        brandRepository.save(brand);
        return brandDTO;
    }

    public void deleteBrandById(Long id){
        brandRepository.deleteById(id);
    }

    public BrandDTO updateBrand(Long id, BrandDTO brandDTO){
        Brand brand = brandRepository.findById(id).orElseThrow(()-> new RuntimeException("Brand not found"));
        brand.setName(brandDTO.getName());
        Brand newBrand = brandRepository.save(brand);
        return brandUtil.mapBrandToDTO(newBrand);
    }

    public BrandDTO updateBrandPartially(Long id, BrandDTO brandDTO){
        Brand brand = brandRepository.findById(id).orElseThrow(()-> new RuntimeException("Brand not found"));
        if (brandDTO.getName() != null) {
            brand.setName(brandDTO.getName());
        }
        Brand newBrand = brandRepository.save(brand);
        return brandUtil.mapBrandToDTO(newBrand);

    }

}
