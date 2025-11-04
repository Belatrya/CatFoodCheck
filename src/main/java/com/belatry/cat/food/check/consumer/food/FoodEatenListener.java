package com.belatry.cat.food.check.consumer.food;

import com.belatry.cat.food.check.consumer.food.dto.FoodEatenMessage;
import com.belatry.cat.food.check.service.api.FoodEatenHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
@KafkaListener(topics = "${kafka.topic.cat.food-eaten.name}",
        autoStartup = "${kafka.topic.cat.food-eaten.auto-startup}",
        groupId = "${spring.kafka.consumer.group-id}")
public class FoodEatenListener {

    private final FoodEatenHandler foodEatenHandler;

    @KafkaHandler
    public void handle(@Header(KafkaHeaders.OFFSET) Long offset,
                       @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
                       @Payload FoodEatenMessage message,
                       Acknowledgment acknowledgment) {
        try {
            log.debug("[partition={},offset={}] Starting: {}", partition, offset, message);
            foodEatenHandler.handle(message);

            acknowledgment.acknowledge();
            log.debug("[partition={},offset={}] Successfully processed and acknowledged", partition, offset);
        } catch (Exception e) {
            log.error("partition={}; offset={}; Process failed", partition, offset);
            log.error(e.getMessage(), e);
            throw e;
        }
    }
}
