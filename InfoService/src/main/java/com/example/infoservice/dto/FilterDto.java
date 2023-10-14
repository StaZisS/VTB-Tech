package com.example.infoservice.dto;

import com.example.infoservice.entity.ServiceDepartmentEnum;
import lombok.Data;

import java.util.List;

@Data
public class FilterDto {
    private String hasRamp;
    private List<ServiceDepartmentEnum> services;
}
