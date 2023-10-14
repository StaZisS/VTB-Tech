package com.example.infoservice.dto;

import com.example.infoservice.entity.AtmsOptionEnum;
import lombok.Data;

import java.util.List;

@Data
public class FilterAtmsDto {
    List<AtmsOptionEnum> atmsOptions;
}
