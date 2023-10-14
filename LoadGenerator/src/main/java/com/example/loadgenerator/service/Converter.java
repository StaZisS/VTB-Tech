package com.example.loadgenerator.service;

import java.util.Collections;
import java.util.List;

public class Converter {
    public static List<Integer> getHours(String openHours) {
        var res = new java.util.ArrayList<Integer>();
        if (openHours != null && !openHours.equals("выходной")){
            var time = openHours.split("-");
            var start = Integer.parseInt(time[0].split(":")[0]);
            var end = Integer.parseInt(time[1].split(":")[0]);
            for (int i = start; i <= end; i++){
                res.add(i);
            }
            return res;
        }
        return Collections.emptyList();
    }
}
