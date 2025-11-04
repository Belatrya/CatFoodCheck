package com.belatry.cat.food.check.producer.report.impl;

import com.belatry.cat.food.check.producer.report.api.MasterReportSender;
import com.belatry.cat.food.check.producer.report.dto.FoodEatenReport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class MasterReportSenderImpl implements MasterReportSender {

    private final String topic;
    private final KafkaTemplate<String, FoodEatenReport> kafkaTemplate;

    public MasterReportSenderImpl(@Value("${kafka.topic.master.create-report}") String topic,
                                  KafkaTemplate<String, FoodEatenReport> kafkaTemplate) {
        this.topic = topic;
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void send(FoodEatenReport message) {
        kafkaTemplate.send(topic, message);
    }
}
