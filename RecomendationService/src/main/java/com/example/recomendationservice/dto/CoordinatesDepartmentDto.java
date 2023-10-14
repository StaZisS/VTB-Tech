package com.example.recomendationservice.dto;

import com.example.recomendationservice.entity.LoadEnum;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CoordinatesDepartmentDto {
    private double latitude;
    private double longitude;
    private UUID id;

    @JsonIgnore
    private double distance;
    @JsonIgnore
    private LoadEnum load;
}
