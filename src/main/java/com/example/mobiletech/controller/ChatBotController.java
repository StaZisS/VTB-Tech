package com.example.mobiletech.controller;


import com.example.mobiletech.entity.ServiceEnum;
import com.example.mobiletech.service.ChatBotService;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.*;

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
