package com.example.chatbot.controller;

import com.example.chatbot.entity.ServiceEnum;
import com.example.chatbot.service.ChatBotService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "ChatBotController", description = "Controller for chat bot")
@RestController
public class ChatBotController {
    private final ChatBotService enumService;

    public ChatBotController(ChatBotService enumService) {
        this.enumService = enumService;
    }

    @Operation(summary = "Получить наиболее релевантные значения для запроса")
    @GetMapping("/chat-bot")
    public List<ServiceEnum> getClosestEnum(@RequestParam("query") String query) {
        return enumService.getClosestServiceEnums(query);
    }

}