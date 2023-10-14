package com.example.chatbot.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ServiceEnum {
    APPLY_FOR_LOAN("Получить кредит"),
    TAKE_MORTGAGE("Взять ипотеку"),
    APPLY_FOR_DEBIT_CARD("Получить дебетовую карту"),
    REPAY_LOAN("Оплатить кредит"),
    MAKE_TRANSFER("Совершить перевод");

    private final String keywords;
}
