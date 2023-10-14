package com.example.statisticservice.service;

import com.example.statisticservice.entity.DaysEntity;
import com.example.statisticservice.entity.LoadEnum;
import com.example.statisticservice.entity.StatisticHour;

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

    public static LoadEnum getLoad(int count){
        if (count < LoadEnum.LOW.getValue()){
            return LoadEnum.LOW;
        }
        if (count < LoadEnum.MEDIUM.getValue()){
            return LoadEnum.MEDIUM;
        }
        return LoadEnum.HIGH;
    }

    public static int parseDayAndHourAndGetLoad(List<DaysEntity> days, String day, int hour){
        var dayEntity = days.stream()
                .filter(d -> d.getDay().equals(day))
                .findFirst();
        if (dayEntity.isEmpty()){
            return 0;
        }
        var daysEntity = dayEntity.get();
        var hourEntity = daysEntity.getHour().stream()
                .filter(h -> h.getHour() == hour)
                .findFirst();
        if (hourEntity.isEmpty()){
            return 0;
        }
        var count = 0;
        for (var serviceCount : hourEntity.get().getHourlyServiceCount()){
            count += serviceCount.getCount();
        }
        return count;
    }

    public static List<StatisticHour> getStatisticADay(DaysEntity daysEntity){
        if(daysEntity == null){
            return Collections.emptyList();
        }
        var res = new java.util.ArrayList<StatisticHour>();
        for (var hour : daysEntity.getHour()){
            var count = 0;
            for (var serviceCount : hour.getHourlyServiceCount()){
                count += serviceCount.getCount();
            }
            res.add(StatisticHour.builder()
                    .hour(hour.getHour())
                    .load(getLoad(count))
                    .build());
        }
        return res;
    }


}
