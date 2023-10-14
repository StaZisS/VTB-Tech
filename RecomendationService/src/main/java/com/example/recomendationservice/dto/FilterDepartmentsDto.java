package com.example.recomendationservice.dto;

import com.example.recomendationservice.entity.ServiceDepartmentEnum;
import lombok.Data;

import java.util.List;

@Data
public class FilterDepartmentsDto {
    private String hasRamp;
    private List<ServiceDepartmentEnum> services;
}
