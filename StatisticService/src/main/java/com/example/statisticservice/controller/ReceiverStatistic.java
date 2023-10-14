package com.example.statisticservice.controller;

import com.example.statisticservice.dto.IncrementDto;
import com.example.statisticservice.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/receiver_statistic")
@RequiredArgsConstructor
public class ReceiverStatistic {
    private final StatisticService statisticService;

    @PostMapping("/increment")
    public ResponseEntity<Void> incrementStatistic(@RequestBody IncrementDto incrementDto){
        statisticService.incrementStatistic(incrementDto);
        return ResponseEntity.ok().build();
    }
}
