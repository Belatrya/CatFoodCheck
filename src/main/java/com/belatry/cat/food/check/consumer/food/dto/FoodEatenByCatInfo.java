package com.belatry.cat.food.check.consumer.food.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.jackson.Jacksonized;

@Getter
@Setter
@Jacksonized
public class FoodEatenByCatInfo {
    private CatName catName;
    private int foodCountGram;
    private int mealCountPerDay;
}
