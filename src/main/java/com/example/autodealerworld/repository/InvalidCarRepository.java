package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.InvalidCar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InvalidCarRepository extends JpaRepository<InvalidCar, Long> {
    Optional<InvalidCar> findByUserId(Long userId);
}
