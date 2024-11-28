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
}
