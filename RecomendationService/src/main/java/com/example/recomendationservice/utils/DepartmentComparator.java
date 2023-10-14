package com.example.recomendationservice.utils;

import com.example.recomendationservice.dto.CoordinatesDepartmentDto;

import java.util.Comparator;

public class DepartmentComparator implements Comparator<CoordinatesDepartmentDto> {
    private static final double DISTANCE_WEIGHT = 1.0;
    private static final double LOAD_WEIGHT = 0.5;


    @Override
    public int compare(CoordinatesDepartmentDto office1, CoordinatesDepartmentDto office2) {
        double relevance1 = calculateRelevance(office1);
        double relevance2 = calculateRelevance(office2);
        return Double.compare(relevance2, relevance1);
    }

    private double calculateRelevance(CoordinatesDepartmentDto office) {
        double distance = office.getDistance();
        double load = office.getLoad().ordinal();
        return DISTANCE_WEIGHT * distance + LOAD_WEIGHT * load;
    }


}