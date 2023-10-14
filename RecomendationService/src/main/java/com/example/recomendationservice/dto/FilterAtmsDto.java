package com.example.recomendationservice.dto;

import com.example.recomendationservice.entity.AtmsOptionEnum;
import lombok.Data;

import java.util.List;

@Data
public class FilterAtmsDto {
    List<AtmsOptionEnum> atmsOptions;
}
