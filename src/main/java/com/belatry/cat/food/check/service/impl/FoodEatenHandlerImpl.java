package com.belatry.cat.food.check.service.impl;

import com.belatry.cat.food.check.config.CatFoodNormProperties;
import com.belatry.cat.food.check.consumer.food.dto.CatName;
import com.belatry.cat.food.check.consumer.food.dto.FoodEatenByCatInfo;
import com.belatry.cat.food.check.consumer.food.dto.FoodEatenMessage;
import com.belatry.cat.food.check.converter.FoodEatenMessageConverter;
import com.belatry.cat.food.check.producer.report.api.MasterReportSender;
import com.belatry.cat.food.check.service.api.FoodEatenHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FoodEatenHandlerImpl implements FoodEatenHandler {
    private static final String CATS_HAD_NOT_EAT_ANYTHING = "Коты ничего не ели, или произошла ошибка диспенсера";
    private static final String CAT_EAT_TOO_MUCH = "Кот %s съел(а) на %d грамм больше еды, чем необходимо. Сделано %d подходов к миске.";
    private static final String CAT_EAT_NOT_ENOUGH = "Кот %s съел(а) на %d грамм меньше еды, чем необходимо. Сделано %d подходов к миске.";


    private final MasterReportSender masterReportSender;
    private final FoodEatenMessageConverter foodEatenMessageConverter;
    private final CatFoodNormProperties catFoodNormProperties;


    @Override
    public void handle(FoodEatenMessage message) {
        if (Objects.isNull(message)) {
            return;
        }

        if (CollectionUtils.isEmpty(message.getFoodEatenByCats())) {
            masterReportSender.send(foodEatenMessageConverter.toFoodEatenReport(CATS_HAD_NOT_EAT_ANYTHING,
                    message.getMealDate()));
        }

        for (FoodEatenByCatInfo foodEatenByCat : message.getFoodEatenByCats()) {
            int catFoodEatenByDailyNorm = getCatFoodEatenByDailyNorm(foodEatenByCat.getCatName(),
                    foodEatenByCat.getFoodCountGram());
            if (catFoodEatenByDailyNorm != 0) {
                String messageToSend;
                if (catFoodEatenByDailyNorm < 0) {
                    messageToSend = CAT_EAT_TOO_MUCH;
                } else {
                    messageToSend = CAT_EAT_NOT_ENOUGH;
                }
                masterReportSender.send(foodEatenMessageConverter.toFoodEatenReport(
                        String.format(messageToSend, foodEatenByCat.getCatName().getDescription(),
                                Math.abs(catFoodEatenByDailyNorm), foodEatenByCat.getMealCountPerDay()),
                        message.getMealDate()));
            }
        }
    }

    private int getCatFoodEatenByDailyNorm(CatName catName, int foodCountGram) {
        int foodCount = 0;
        if (catName == CatName.FELIX) {
            foodCount = catFoodNormProperties.getFelix() - foodCountGram;
        } else if (catName == CatName.KIRA) {
            foodCount = catFoodNormProperties.getKira() - foodCountGram;
        }
        return foodCount;
    }
}
