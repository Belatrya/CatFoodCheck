package com.belatry.cat.food.check.producer.report.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;

@Getter
@Builder
@Jacksonized
public class FoodEatenReport {
    private String message;
    private LocalDate mealDate;
}
