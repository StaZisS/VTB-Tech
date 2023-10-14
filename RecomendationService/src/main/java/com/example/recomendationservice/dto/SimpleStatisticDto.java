package com.example.recomendationservice.dto;

import com.example.recomendationservice.entity.LoadEnum;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SimpleStatisticDto {
    private UUID id;
    private LoadEnum load;
}
