package com.example.recomendationservice.service;

import com.example.recomendationservice.dto.CoordinatesDepartmentDto;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CalculateDistance {
    public List<CoordinatesDepartmentDto>
    findNearestOffices(List<CoordinatesDepartmentDto> offices ,Double latitude, Double longitude, int numberOfOffices) {

        if (offices.isEmpty()) {
            return new ArrayList<>();
        }

        for (var office : offices) {
            double distance = calculateHaversineDistance(latitude, longitude, office.getLatitude(), office.getLongitude());
            office.setDistance(distance);
        }

        offices.sort(Comparator.comparingDouble(CoordinatesDepartmentDto::getDistance));
        return offices.subList(0, Math.min(numberOfOffices, offices.size()));
    }

    private double calculateHaversineDistance(double lat1, double lon1, double lat2, double lon2) {
        var firstLatitude = Math.toRadians(lat1);
        var firstLongitude = Math.toRadians(lon1);
        var secondLatitude = Math.toRadians(lat2);
        var secondLongitude = Math.toRadians(lon2);
        double dLat = secondLatitude - firstLatitude;
        double dLon = secondLongitude - firstLongitude;
        double a = Math.sin(dLat / 2) * Math.sin(dLat / 2) +
                Math.cos(lat1) * Math.cos(lat2) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double R = 6371;
        return R * c;
    }
}
