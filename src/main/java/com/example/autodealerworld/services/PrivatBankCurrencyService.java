package com.example.autodealerworld.services;

import com.example.autodealerworld.entity.CurrencyRate;
import com.example.autodealerworld.entity.dto.PrivatBankRateDTO;
import com.example.autodealerworld.entity.enums.Currency;
import com.example.autodealerworld.repository.CurrencyRateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class PrivatBankCurrencyService {

    private final RestTemplate restTemplate;

    private final CurrencyRateRepository currencyRateRepository;

    private static final String PRIVATBANK_API_URL = "https://api.privatbank.ua/p24api/pubinfo?json&exchange&coursid=5";

    public CurrencyRate getOrUpdateCurrencyRates(Currency baseCurrency){
        CurrencyRate rate = currencyRateRepository.findByBaseCurrency(baseCurrency).orElse(null);

        if (rate == null || rate.getLastTimeUpdate().isBefore(LocalDateTime.now().minusDays(1))) {
            updateCurrencyRates();
            rate = currencyRateRepository.findByBaseCurrency(baseCurrency)
                    .orElseThrow(()-> new RuntimeException("Failed to fetch updated rates"));
        }
        return rate;
    }

    public void updateCurrencyRates() {
        ResponseEntity<PrivatBankRateDTO[]> response = restTemplate.getForEntity(PRIVATBANK_API_URL, PrivatBankRateDTO[].class);
        PrivatBankRateDTO[] rates = response.getBody();

        if (rates == null || rates.length == 0) {
            throw new RuntimeException("Failed to fetch currency rates from PrivatBank");
        }

        Map<String, Double> rateMap = new HashMap<>();
        for (PrivatBankRateDTO rate : rates) {
            rateMap.put(rate.getCcy(), Double.parseDouble(rate.getSale()));
        }

        if (!rateMap.containsKey("USD") || !rateMap.containsKey("EUR")) {
            throw new RuntimeException("Missing required currency rates from PrivatBank API");
        }

        updateRate(Currency.UAH, 1.0, 1 / rateMap.get("USD"), 1 / rateMap.get("EUR"));

        updateRate(Currency.USD, rateMap.get("USD"), 1.0, rateMap.get("EUR") / rateMap.get("USD"));

        updateRate(Currency.EUR, rateMap.get("EUR"), rateMap.get("USD") / rateMap.get("EUR"), 1.0);
    }
    private Double roundToTwoDecimalPlaces(Double value) {
        if (value == null) {
            return null;
        }
        return BigDecimal.valueOf(value)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }

    private void updateRate(Currency baseCurrency, Double toUahRate, Double toUsdRate, Double toEurRate) {
        CurrencyRate rate = currencyRateRepository.findByBaseCurrency(baseCurrency)
                .orElseGet(() -> new CurrencyRate(null, baseCurrency, null, null, LocalDateTime.now()));

        rate.setToUSDRate(roundToTwoDecimalPlaces(toUsdRate));
        rate.setToEURRate(roundToTwoDecimalPlaces(toEurRate));
        rate.setLastTimeUpdate(LocalDateTime.now());

        currencyRateRepository.save(rate);
    }

    public Map<Currency, Double> convertCurrencyPrices(Double amount, Currency baseCurrency) {
        CurrencyRate rate = getOrUpdateCurrencyRates(Currency.UAH); // UAH як базова валюта

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


}
