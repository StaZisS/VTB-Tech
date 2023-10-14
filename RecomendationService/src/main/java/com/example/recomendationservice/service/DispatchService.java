package com.example.recomendationservice.service;

import com.example.recomendationservice.dto.CoordinatesDepartmentDto;
import com.example.recomendationservice.dto.DepartmentsIdDto;
import com.example.recomendationservice.dto.FilterDto;
import com.example.recomendationservice.dto.SimpleStatisticDto;
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

    public List<CoordinatesDepartmentDto>
    getPrioritizedOffices(double latitude, double longitude, int countOffices, String time, FilterDto filterDto){
        var offices = getOfficesFromInfoService(filterDto);
        var nearestOffice = calculateDistance.findNearestOffices(offices, latitude, longitude, countOffices);
        var departmentsIdDto = DepartmentsIdDto.builder()
                .ids(nearestOffice.stream().map(CoordinatesDepartmentDto::getId).toList())
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



    private List<CoordinatesDepartmentDto>
    getOfficesFromInfoService(FilterDto filterDto){
        if (filterDto == null){
            return webClientBuilder.build()
                    .post()
                    .uri(infoServiceUrl + "/offices/coordinates")
                    .retrieve()
                    .bodyToFlux(CoordinatesDepartmentDto.class)
                    .collectList()
                    .block();
        }
        return webClientBuilder.build()
                .post()
                .uri(infoServiceUrl + "/offices/coordinates")
                .bodyValue(filterDto)
                .retrieve()
                .bodyToFlux(CoordinatesDepartmentDto.class)
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

}
