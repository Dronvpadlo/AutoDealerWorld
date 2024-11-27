package com.example.autodealerworld.controllers.admin;

import com.example.autodealerworld.entity.enums.Currency;
import com.example.autodealerworld.services.CurrencyRateService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/currency")
@RequiredArgsConstructor
public class CurrencyRateController {

    private final CurrencyRateService currencyRateService;

    @PostMapping("/update")
    public ResponseEntity<String> updateRates(
            @RequestParam Currency baseCurrency,
            @RequestParam Double toUsd,
            @RequestParam Double toEur){
        currencyRateService.updateCurrencyRates(toUsd, toEur, baseCurrency);
        return new ResponseEntity<>("Currency rates updated successfully", HttpStatus.OK);
    }

    @GetMapping("/convert")
    public ResponseEntity<Map<Currency, Double>> convertPrice(
            @RequestParam Double amount,
            @RequestParam Currency baseCurrency){
        Map<Currency, Double> prices = currencyRateService.calculatePrices(amount, baseCurrency);
        return new ResponseEntity<>(prices, HttpStatus.OK);
    }
}
