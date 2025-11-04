package com.belatry.cat.food.check.service.api;

import com.belatry.cat.food.check.consumer.food.dto.FoodEatenMessage;

public interface FoodEatenHandler {
    void handle(FoodEatenMessage message);
}
