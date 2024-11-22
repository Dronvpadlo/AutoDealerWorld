package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.Brand;
import com.example.autodealerworld.entity.dto.BrandDTO;
import com.example.autodealerworld.util.BrandUtil;
import com.example.autodealerworld.repository.BrandRepository;
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
        System.out.println("DTO: " + brandDTO);
        Brand brand = brandUtil.mapBrandToEntity(brandDTO);
        brandRepository.save(brand);
        return brandDTO;
    }

    public Brand findBrandById(Long id) {
        return brandRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Brand with id " + id + " not found"));
    }
}
