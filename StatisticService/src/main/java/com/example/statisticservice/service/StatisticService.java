package com.example.statisticservice.service;

import com.example.statisticservice.dto.IncrementDto;
import com.example.statisticservice.entity.*;
import com.example.statisticservice.repository.StatisticRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class StatisticService {
    private final StatisticRepo statisticRepo;

    public void incrementStatistic(IncrementDto incrementDto) {
        Optional<StatisticEntity> statisticEntity = statisticRepo.findById(incrementDto.getId());
        if(statisticEntity.isEmpty()){
            return;
        }

        var statistic = statisticEntity.get();
        var daysEntityOptional =
                statistic.getDays().stream()
                        .filter(daysEntity -> daysEntity.getDay().equals(incrementDto.getDay()))
                        .findFirst();
        if(daysEntityOptional.isEmpty()){
            return;
        }

        var daysEntity = daysEntityOptional.get();
        var hourEntityOptional =
                daysEntity.getHour().stream()
                        .filter(hourEntity -> hourEntity.getHour() == incrementDto.getHour())
                        .findFirst();
        if(hourEntityOptional.isEmpty()){
            return;
        }

        var hourEntity = hourEntityOptional.get();
        var hourlyServiceCountOptional =
                hourEntity.getHourlyServiceCount().stream()
                        .filter(hourlyServiceCount -> hourlyServiceCount.getServiceType().equals(incrementDto.getService()))
                        .findFirst();
        if(hourlyServiceCountOptional.isEmpty()){
            return;
        }

        var hourlyServiceCount = hourlyServiceCountOptional.get();
        hourlyServiceCount.setCount(hourlyServiceCount.getCount() + 1);
        statisticRepo.save(statistic);
    }
}
