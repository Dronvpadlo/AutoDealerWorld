package com.example.autodealerworld.entity;

import com.example.autodealerworld.entity.enums.Currency;
import com.example.autodealerworld.entity.enums.CarStatus;
import jakarta.persistence.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
@Setter
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", nullable = false)
    private Brand brand;

    private double price;

    @Enumerated(EnumType.STRING)
    private Currency currency;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id", nullable = false)
    private Region region;

    @Enumerated(EnumType.STRING)
    private CarStatus carStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    public Car(Brand brand, double price, Currency currency, Region region, CarStatus carStatus, UserEntity owner) {
        this.brand = brand;
        this.price = price;
        this.currency = currency;
        this.region = region;
        this.carStatus = carStatus;
        this.owner = owner;
    }
}
