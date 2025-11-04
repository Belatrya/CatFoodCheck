package com.belatry.cat.food.check.consumer.food.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Jacksonized
public class FoodEatenMessage {
    private List<FoodEatenByCatInfo> foodEatenByCats = new ArrayList<>();
    private LocalDate mealDate;
}
