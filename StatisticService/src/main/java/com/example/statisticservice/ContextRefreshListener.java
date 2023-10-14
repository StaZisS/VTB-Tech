package com.example.statisticservice;

import com.example.statisticservice.dto.DepartmentTimeAndServicesDto;
import com.example.statisticservice.entity.DaysEntity;
import com.example.statisticservice.entity.HourEntity;
import com.example.statisticservice.entity.HourlyServiceCount;
import com.example.statisticservice.entity.StatisticEntity;
import com.example.statisticservice.repository.StatisticRepo;
import com.example.statisticservice.service.Converter;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.core.Ordered.LOWEST_PRECEDENCE;

@Component
@Order(LOWEST_PRECEDENCE)
@RequiredArgsConstructor
public class ContextRefreshListener implements ApplicationListener<ContextRefreshedEvent> {
    private final WebClient.Builder webClientBuilder;
    private final StatisticRepo statisticRepo;

    @Value("${info.service.url}")
    private String infoServiceUrl;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        initTable();
    }

    private void initTable() {
        var departments = getDepartmentTimeAndServices();
        for (var department : departments) {
            var statisticEntity = StatisticEntity.builder()
                    .idDepartment(department.getId())
                    .build();
            statisticEntity.setDays(new ArrayList<>());
            var days = department.getOpenHours();
            for (var day : days) {
                var daysEntity = DaysEntity.builder()
                        .day(day.getDays())
                        .statisticEntity(statisticEntity)
                        .build();
                daysEntity.setHour(new ArrayList<>());
                var hoursNonParseStr = day.getHours();
                var hours = Converter.getHours(hoursNonParseStr);
                for (var hour : hours) {
                    var hourEntity = HourEntity.builder()
                            .hour(hour)
                            .daysEntity(daysEntity)
                            .build();
                    hourEntity.setHourlyServiceCount(new ArrayList<>());
                    var hourlyServiceCounts = department.getServices();
                    for (var hourlyServiceCount : hourlyServiceCounts) {
                        var hourlyServiceCountEntity = HourlyServiceCount.builder()
                                .serviceType(hourlyServiceCount.getServiceDepartmentEnum())
                                .count(0)
                                .hourEntity(hourEntity)
                                .build();
                        hourEntity.getHourlyServiceCount().add(hourlyServiceCountEntity);
                    }
                    daysEntity.getHour().add(hourEntity);
                }
                statisticEntity.getDays().add(daysEntity);
            }
            statisticRepo.save(statisticEntity);
        }
    }

    public List<DepartmentTimeAndServicesDto> getDepartmentTimeAndServices() {
        return webClientBuilder.build()
                .get()
                .uri(infoServiceUrl)
                .retrieve()
                .bodyToFlux(DepartmentTimeAndServicesDto.class)
                .collectList()
                .block();
    }
}
