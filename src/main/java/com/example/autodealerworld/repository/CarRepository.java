package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.Region;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select p from Car p where p.price> :minPrice and p.price < :maxPrice")
    List<Car> findCarsByPriceBetween(Double minPrice, Double maxPrice);

    List<Car> findCarsByPriceLessThan(Double maxPrice);

    List<Car> findCarsByPriceGreaterThan(Double minPrice);

    //Optional<List<Car>> findCarsByBrand(String brandName);

    /*Optional<List<Car>> findCarsByRegion(String regionName);

    Optional<List<Car>> findCarsByRegionAndBrand(String regionName, String brandName);*/

}
