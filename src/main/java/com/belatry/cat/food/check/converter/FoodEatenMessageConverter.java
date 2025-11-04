package com.belatry.cat.food.check.converter;

import com.belatry.cat.food.check.producer.report.dto.FoodEatenReport;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class FoodEatenMessageConverter {

    public FoodEatenReport toFoodEatenReport(String message, LocalDate mealDate) {
        return FoodEatenReport.builder()
                .message(message)
                .mealDate(mealDate)
                .build();
    }
}
