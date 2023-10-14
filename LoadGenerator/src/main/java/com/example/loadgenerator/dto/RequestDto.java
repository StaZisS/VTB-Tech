package com.example.loadgenerator.dto;

import com.example.loadgenerator.entity.ServiceDepartmentEnum;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class RequestDto {
    private UUID id;
    private String day;
    private int hour;
    private ServiceDepartmentEnum service;
}
