package com.example.loadgenerator.service;

import com.example.loadgenerator.dto.DepartmentTimeAndServicesDto;
import com.example.loadgenerator.dto.RequestDto;
import com.example.loadgenerator.entity.ServiceDepartmentEnum;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class DataGeneratorService {
    private final WebClient.Builder webClientBuilder;
    private List<DepartmentTimeAndServicesDto> departmentTimeAndServices;
    private final Random random = new Random();

    public RequestDto generate() {
        var department = departmentTimeAndServices.get(random.nextInt(departmentTimeAndServices.size()));
        var day = department.getOpenHours().get(random.nextInt(department.getOpenHours().size()));
        var hours = Converter.getHours(day.getHours());
        var hour = 0;
        try {
            hour = hours.get(random.nextInt(hours.size()));
        } catch (Exception e) {
            hour = 0;
        }
        var service = ServiceDepartmentEnum.APPLY_FOR_DEBIT_CARD;
        try{
            service = department.getServices().get(random.nextInt(department.getServices().size())).getServiceDepartmentEnum();
        } catch (Exception e) {
            service = ServiceDepartmentEnum.APPLY_FOR_DEBIT_CARD;
        }
        return RequestDto.builder()
                .id(department.getId())
                .day(day.getDays())
                .hour(hour)
                .service(service)
                .build();
    }

    public void loadData(){
        departmentTimeAndServices = getDepartmentTimeAndServices();
    }

    public List<DepartmentTimeAndServicesDto> getDepartmentTimeAndServices() {
        String urlDepartment = "http://localhost:8080/info_service/departments";
        return webClientBuilder.build()
                .get()
                .uri(urlDepartment)
                .retrieve()
                .bodyToFlux(DepartmentTimeAndServicesDto.class)
                .collectList()
                .block();
    }
}
