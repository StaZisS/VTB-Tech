package com.example.statisticservice.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StatisticHour {
    private int hour;
    private LoadEnum load;
}
