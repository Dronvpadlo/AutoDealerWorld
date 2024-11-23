package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.Car;
import com.example.autodealerworld.entity.enums.CarStatus;
import com.example.autodealerworld.entity.enums.RegionCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    @Query("SELECT c from Car c WHERE " +
    "(:brand IS NULL OR c.brand.name = :brand) AND "+
    "(:model IS NULL OR c.model.name = :model) AND "+
    "(:region IS NULL OR c.region.name = :region) AND "+
    "(:minPrice IS NULL OR c.price >= :minPrice) AND "+
    "(:maxPrice IS NULL OR c.price <= :maxPrice) AND "+
    "(:minYear IS NULL OR c.year >= :minYear) AND "+
    "(:maxYear IS NULL OR c.year <= :maxYear)AND "+
    "(:carStatus IS NULL OR c.carStatus = :carStatus)")
    List<Car> findCarsByFilters(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("region") String region,
            @Param("minPrice") Double minPrice,
            @Param("maxPrice") Double maxPrice,
            @Param("minYear") Long minYear,
            @Param("maxYear") Long maxYear,
            @Param("carStatus")CarStatus carStatus
            );
    @Query("SELECT AVG(c.price) FROM Car c " +
            "WHERE (:brand IS NULL OR c.brand.name = :brand) " +
            "AND (:model IS NULL OR c.model.name = :model) " +
            "AND (:region IS NULL OR c.region.name = :region) " +
            "AND (:year IS NULL OR c.year = :year)" +
            "AND (:regionCode IS NULL OR c.region.code = :regionCode)"
    )
    Double findAveragePriceByBrand(
            @Param("brand") String brand,
            @Param("model") String model,
            @Param("region") String region,
            @Param("year") Long year,
            @Param("regionCode")RegionCode regionCode
    );

    @Query("SELECT AVG(c.price) FROM Car c")
    Double findGlobalAveragePrice();

}
