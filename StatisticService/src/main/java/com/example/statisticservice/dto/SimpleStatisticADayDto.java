package com.example.statisticservice.dto;

import com.example.statisticservice.entity.StatisticHour;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class SimpleStatisticADayDto {
    private List<StatisticHour> hours;
}
