package com.example.autodealerworld.controllers.admin;

import com.example.autodealerworld.entity.CurrencyRate;
import com.example.autodealerworld.repository.CurrencyRateRepository;
import com.example.autodealerworld.services.PrivatBankCurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/currencies")
@RequiredArgsConstructor
public class CurrencyController {

    private final PrivatBankCurrencyService privatBankCurrencyService;
    private final CurrencyRateRepository currencyRateRepository;

    @GetMapping("/rates")
    public ResponseEntity<List<CurrencyRate>> getCurrencyRates() {
        List<CurrencyRate> rates = currencyRateRepository.findAll();
        if (rates.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(rates, HttpStatus.OK);

    }
    @PostMapping("/update")
    public ResponseEntity<String> updateCurrencyRates() {
        try {
            privatBankCurrencyService.updateCurrencyRates();
            return new ResponseEntity<>("Currency rates updated successfully", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Failed to update currency rates: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
