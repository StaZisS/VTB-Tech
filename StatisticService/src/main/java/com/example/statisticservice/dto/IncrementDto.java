package com.example.statisticservice.dto;

import com.example.statisticservice.entity.ServiceDepartmentEnum;
import lombok.Data;

import java.util.UUID;

@Data
public class IncrementDto {
    private UUID id;
    private String day;
    private int hour;
    private ServiceDepartmentEnum service;
}
