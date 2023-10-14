package com.example.recomendationservice.utils;

import com.example.recomendationservice.dto.CoordinatesDto;

import java.util.Comparator;

public class DepartmentComparator implements Comparator<CoordinatesDto> {
    private static final double DISTANCE_WEIGHT = 1.0;
    private static final double LOAD_WEIGHT = 0.5;


    @Override
    public int compare(CoordinatesDto office1, CoordinatesDto office2) {
        double relevance1 = calculateRelevance(office1);
        double relevance2 = calculateRelevance(office2);
        return Double.compare(relevance2, relevance1);
    }

    private double calculateRelevance(CoordinatesDto office) {
        double distance = office.getDistance();
        double load = office.getLoad().ordinal();
        return DISTANCE_WEIGHT * distance + LOAD_WEIGHT * load;
    }


}