package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("select p from Car p where p.price> :minPrice and p.price < :maxPrice")
    List<Car> findCarsByPriceBetween(double minPrice, double maxPrice);

    List<Car> findCarsByPriceLessThan(double maxPrice);

    List<Car> findCarsByPriceGreaterThan(double minPrice);
}
