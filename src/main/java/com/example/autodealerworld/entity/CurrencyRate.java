package com.example.autodealerworld.entity;

import com.example.autodealerworld.entity.enums.Currency;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "currency_rates")
public class CurrencyRate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long currencyId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, unique = true)
    private Currency baseCurrency;

    @Column(nullable = false)
    private Double toUSDRate;

    @Column(nullable = false)
    private Double toEURRate;

    @Column(nullable = false)
    private LocalDateTime lastTimeUpdate;
}
