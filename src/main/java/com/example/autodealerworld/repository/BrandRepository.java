package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {
    boolean existsBrandByName(String name);
}
