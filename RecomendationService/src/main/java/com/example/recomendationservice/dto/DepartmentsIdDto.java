package com.example.recomendationservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
@Builder
public class DepartmentsIdDto {
    private List<UUID> ids;
    private String day;
    private int hour;
}
