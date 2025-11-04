package com.belatry.cat.food.check.producer.report.api;

import com.belatry.cat.food.check.producer.report.dto.FoodEatenReport;

public interface MasterReportSender {
    void send(FoodEatenReport message);
}
