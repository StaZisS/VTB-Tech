package com.example.chatbot.controller;

import com.example.chatbot.entity.ServiceEnum;
import com.example.chatbot.service.ChatBotService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ChatBotController {
    private final ChatBotService enumService;

    public ChatBotController(ChatBotService enumService) {
        this.enumService = enumService;
    }

    @GetMapping("/chat-bot")
    public List<ServiceEnum> getClosestEnum(@RequestParam("query") String query) {
        return enumService.getClosestServiceEnums(query);
    }

}