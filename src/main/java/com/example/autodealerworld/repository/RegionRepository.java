package com.example.autodealerworld.repository;


import com.example.autodealerworld.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;



@Repository
public interface RegionRepository extends JpaRepository<Region, Long> {
}
