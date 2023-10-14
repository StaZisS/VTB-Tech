package com.example.statisticservice.controller;

import com.example.statisticservice.dto.DepartmentsIdDto;
import com.example.statisticservice.dto.SimpleStatisticADayDto;
import com.example.statisticservice.dto.SimpleStatisticDto;
import com.example.statisticservice.entity.StatisticEntity;
import com.example.statisticservice.repository.StatisticRepo;
import com.example.statisticservice.service.Converter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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

    @PostMapping("/offices")
    public ResponseEntity<List<SimpleStatisticDto>> getOfficesStatistic(@RequestBody DepartmentsIdDto departmentsIdDto){
        return ResponseEntity.ok(
                statisticRepo.findAllById(departmentsIdDto.getIds())
                        .stream()
                        .map(statisticEntity ->
                                SimpleStatisticDto.builder()
                                        .id(statisticEntity.getIdDepartment())
                                        .load(Converter.getLoad(
                                                Converter.parseDayAndHourAndGetLoad(
                                                        statisticEntity.getDays(), departmentsIdDto.getDay(), departmentsIdDto.getHour())
                                        )
                                        ).build()
                        ).toList()
        );
    }

    @GetMapping("/office/{id}")
    public ResponseEntity<SimpleStatisticADayDto> getStatisticADay(
            @PathVariable UUID id,
            @RequestParam String day
    ){
        return statisticRepo.findById(id)
                .map(statisticEntity -> SimpleStatisticADayDto.builder()
                        .hours(
                                Converter.getStatisticADay(
                                        statisticEntity.getDays().stream()
                                                .filter(dayEntity -> dayEntity.getDay().equals(day))
                                                .findFirst()
                                                .orElse(null)
                                )
                        )
                        .build()).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
