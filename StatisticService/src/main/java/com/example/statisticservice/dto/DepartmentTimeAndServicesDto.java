package com.example.statisticservice.dto;

import com.example.statisticservice.entity.ServiceDepartmentEnum;
import com.example.statisticservice.entity.ServiceEnumWrapper;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DepartmentTimeAndServicesDto {
    private UUID id;
    private List<OpenHoursDto> openHours;
    private List<OpenHoursDto> openHoursIndividual;
    private List<ServiceEnumWrapper> services;
}
