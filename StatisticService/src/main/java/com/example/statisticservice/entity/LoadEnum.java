package com.example.statisticservice.entity;

public enum LoadEnum {
    LOW(3),
    MEDIUM(8),
    HIGH(Integer.MAX_VALUE),
    ;
    private int value;
    LoadEnum(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
