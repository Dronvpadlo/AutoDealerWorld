package com.example.autodealerworld.repository;


import com.example.autodealerworld.entity.CarView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CarViewRepository extends JpaRepository<CarView, Long> {
    @Query ("SELECT COUNT(v) FROM CarView v WHERE v.car.id = :carId")
    Long countCarViewsByCarId(@Param("carId") Long carId);

    boolean existsByCarIdAndViewerIp(Long carId, String viewerIp);
}
