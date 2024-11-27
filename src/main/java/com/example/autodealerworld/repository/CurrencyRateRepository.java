package com.example.autodealerworld.repository;

import com.example.autodealerworld.entity.CurrencyRate;
import com.example.autodealerworld.entity.enums.Currency;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CurrencyRateRepository extends JpaRepository<CurrencyRate, Long> {
    Optional<CurrencyRate> findByBaseCurrency(Currency currency);
}
