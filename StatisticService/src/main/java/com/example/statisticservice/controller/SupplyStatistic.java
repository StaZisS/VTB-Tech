package com.example.statisticservice.controller;

import com.example.statisticservice.entity.StatisticEntity;
import com.example.statisticservice.repository.StatisticRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/supply_statistic")
@RequiredArgsConstructor
public class SupplyStatistic {
    private final StatisticRepo statisticRepo;

    @GetMapping("/offices/{id}")
    public ResponseEntity<StatisticEntity> getOfficeStatistic(@PathVariable UUID id){
        return statisticRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
