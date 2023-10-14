package com.example.statisticservice.dto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DepartmentsIdDto {
    private List<UUID> ids;
    private String day;
    private int hour;
}
