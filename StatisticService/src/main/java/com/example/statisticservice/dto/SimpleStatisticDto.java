package com.example.statisticservice.dto;

import com.example.statisticservice.entity.LoadEnum;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class SimpleStatisticDto {
    private UUID id;
    private LoadEnum load;
}
