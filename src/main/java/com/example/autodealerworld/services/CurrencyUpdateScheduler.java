package com.example.autodealerworld.services;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@EnableScheduling
@Service
@RequiredArgsConstructor
public class CurrencyUpdateScheduler {

    private final PrivatBankCurrencyService privatBankCurrencyService;

    @Scheduled(cron = "0 0 1 * * ?")
    public void updateCurrencyRates(){
        privatBankCurrencyService.updateCurrencyRates();
    }
}
