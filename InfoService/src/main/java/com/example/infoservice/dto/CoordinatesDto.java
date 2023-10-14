package com.example.infoservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class CoordinatesDto {
    private double latitude;
    private double longitude;
    private UUID id;
}
