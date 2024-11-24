package com.example.autodealerworld.services;

import com.example.autodealerworld.repository.CarViewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class CarViewService {

    private final CarViewRepository carViewRepository;

    public Long getViewsByCarIdAndPeriod(Long carId, String period){
        LocalDateTime startDate;

        if (period == null || period.trim().isEmpty()) {
            return carViewRepository.countCarViewsByCarId(carId);
        }
        startDate = switch (period.toLowerCase()) {
            case "day" -> LocalDateTime.now().minusDays(1);
            case "month" -> LocalDateTime.now().minusMonths(1);
            case "week" -> LocalDateTime.now().minusWeeks(1);
            default -> throw new IllegalArgumentException("Period in incorrect. Use 'day', 'week' or 'month'.");
            };

        return carViewRepository.countViewsByCarIdAndDate(carId, startDate);
    }
}
