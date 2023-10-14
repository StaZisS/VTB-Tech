package com.example.mobiletech.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceEnum {
    GET_CREDIT("Получить кредит"),
    GET_MORTGAGE("Взять ипотеку"),
    GET_DEBIT_CARD("Получить дебетовую карту"),
    PAY_CREDIT("Оплатить кредит"),
    MAKE_TRANSFER("Совершить перевод");

    private final String keywords;
}
