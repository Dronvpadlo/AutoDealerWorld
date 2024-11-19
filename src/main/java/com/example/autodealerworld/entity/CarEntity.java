package com.example.autodealerworld.entity;

import com.example.autodealerworld.entity.enums.Currency;
import com.example.autodealerworld.entity.enums.CarStatus;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import javax.swing.plaf.synth.Region;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class CarEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String brand;

    private String model;

    private double price;

    private Currency currency;

    private RegionEntity region;

    private String city;

    private CarStatus carStatus;

    private Long ownerId;
}
