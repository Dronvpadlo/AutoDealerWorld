package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.CurrencyRate;
import com.example.autodealerworld.entity.enums.Currency;
import com.example.autodealerworld.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;

    public CurrencyRate getCurrencyRate(Currency baseCurrency){
        return currencyRateRepository.findByBaseCurrency(baseCurrency)
                .orElseThrow(() -> new RuntimeException("Currency rete is not exist " + baseCurrency));
    }

    public Map<Currency, Double> calculatePrices(Double amount, Currency baseCurrency){
        CurrencyRate rate = getCurrencyRate(baseCurrency);
        Map<Currency, Double> prices = new HashMap<>();

        if (baseCurrency == Currency.USD) {
            prices.put(Currency.USD, amount);
            prices.put(Currency.EUR, amount * rate.getToEURRate());
            prices.put(Currency.UAH, amount * rate.getToUSDRate());
        } else if (baseCurrency == Currency.EUR) {
            prices.put(Currency.EUR, amount);
            prices.put(Currency.USD, amount / rate.getToEURRate());
            prices.put(Currency.UAH, (amount / rate.getToEURRate()) * rate.getToUSDRate());
        } else if (baseCurrency == Currency.UAH) {
            prices.put(Currency.UAH, amount);
            prices.put(Currency.USD, amount / rate.getToUSDRate());
            prices.put(Currency.EUR, (amount / rate.getToUSDRate()) * rate.getToEURRate());
        }
        return prices;
    }

    public void updateCurrencyRates(Double toUsd, Double toEur, Currency baseCurrency){
        CurrencyRate rate = currencyRateRepository.findByBaseCurrency(baseCurrency)
                .orElseGet(() -> new CurrencyRate(null, baseCurrency, null, null, LocalDateTime.now()));

        rate.setToUSDRate(toUsd);
        rate.setToEURRate(toEur);
        rate.setLastTimeUpdate(LocalDateTime.now());
        currencyRateRepository.save(rate);
    }
}
