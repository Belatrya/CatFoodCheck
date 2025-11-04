package com.belatry.cat.food.check.consumer.food.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CatName {
    FELIX("Феликс"),
    KIRA("Кира");

    private final String description;
}
