package com.example.recomendationservice.service;

import java.sql.Timestamp;

public class Converter {
    public static String getDayFromTimestamp(Timestamp timestamp){
        return switch (timestamp.getDay()) {
            case 0 -> "пн";
            case 1 -> "вт";
            case 2 -> "ср";
            case 3 -> "чт";
            case 4 -> "пт";
            case 5 -> "сб";
            case 6 -> "вс";
            default -> throw new IllegalStateException("Unexpected value: " + timestamp.getDay());
        };
    }

    public static int getHourFromTimestamp(Timestamp timestamp){
        return timestamp.getHours();
    }
}
