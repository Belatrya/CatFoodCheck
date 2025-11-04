package com.belatry.cat.food.check.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "cat-food-check.food-norm")
public class CatFoodNormProperties {
    private int felix;
    private int kira;
}
