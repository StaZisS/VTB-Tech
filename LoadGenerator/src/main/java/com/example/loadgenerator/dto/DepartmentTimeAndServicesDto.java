package com.example.loadgenerator.dto;

import lombok.Builder;
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
