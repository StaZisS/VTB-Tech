package com.example.recomendationservice.service;

import com.example.recomendationservice.dto.*;
import com.example.recomendationservice.utils.DepartmentComparator;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DispatchService {
    private final WebClient.Builder webClientBuilder;
    private final CalculateDistance calculateDistance;

    @Value("${url.info.service}")
    private String infoServiceUrl;
    @Value("${url.statistic.service}")
    private String statisticServiceUrl;

    public List<CoordinatesDto>
    getPrioritizedOffices(double latitude, double longitude, int countOffices, String time, FilterDepartmentsDto filterDepartmentsDto) {
        var offices = getOfficesFromInfoService(filterDepartmentsDto);
        var nearestOffice = calculateDistance.findNearestOffices(offices, latitude, longitude, countOffices);
        var departmentsIdDto = DepartmentsIdDto.builder()
                .ids(nearestOffice.stream().map(CoordinatesDto::getId).toList())
                .day(Converter.getDayFromTimestamp(Timestamp.valueOf(time)))
                .hour(Converter.getHourFromTimestamp(Timestamp.valueOf(time)))
                .build();
        var statisticNearestOffice = getStatisticFromStatisticService(departmentsIdDto);
        nearestOffice.forEach(office -> {
            var statistic = statisticNearestOffice.stream()
                    .filter(statisticDto -> statisticDto.getId().equals(office.getId()))
                    .findFirst().get();
            office.setLoad(statistic.getLoad());
        });
        nearestOffice.sort(Collections.reverseOrder(new DepartmentComparator()));
        return nearestOffice;
    }

    public List<CoordinatesDto>
    getPrioritizedAtms(double latitude, double longitude, int countOffices, String time, FilterAtmsDto filterAtmsDto) {
        var atms = getAtmsFromInfoService(filterAtmsDto);
        return calculateDistance.findNearestOffices(atms, latitude, longitude, countOffices);
    }



    private List<CoordinatesDto>
    getOfficesFromInfoService(FilterDepartmentsDto filterDepartmentsDto){
        if (filterDepartmentsDto == null){
            return webClientBuilder.build()
                    .post()
                    .uri(infoServiceUrl + "/offices/coordinates")
                    .retrieve()
                    .bodyToFlux(CoordinatesDto.class)
                    .collectList()
                    .block();
        }
        return webClientBuilder.build()
                .post()
                .uri(infoServiceUrl + "/offices/coordinates")
                .bodyValue(filterDepartmentsDto)
                .retrieve()
                .bodyToFlux(CoordinatesDto.class)
                .collectList()
                .block();
    }

    private List<SimpleStatisticDto>
    getStatisticFromStatisticService(DepartmentsIdDto departmentsIdDto){
        return webClientBuilder.build()
                .post()
                .uri(statisticServiceUrl + "/offices")
                .bodyValue(departmentsIdDto)
                .retrieve()
                .bodyToFlux(SimpleStatisticDto.class)
                .collectList()
                .block();
    }

    private List<CoordinatesDto>
    getAtmsFromInfoService(FilterAtmsDto filterAtmsDto){
        if (filterAtmsDto == null){
            return webClientBuilder.build()
                    .post()
                    .uri(infoServiceUrl + "/atms/coordinates")
                    .retrieve()
                    .bodyToFlux(CoordinatesDto.class)
                    .collectList()
                    .block();
        }
        return webClientBuilder.build()
                .post()
                .uri(infoServiceUrl + "/atms/coordinates")
                .bodyValue(filterAtmsDto)
                .retrieve()
                .bodyToFlux(CoordinatesDto.class)
                .collectList()
                .block();
    }



}
