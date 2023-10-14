package com.example.chatbot.service;


import com.example.chatbot.entity.ServiceEnum;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ChatBotService {

    public List<ServiceEnum> getClosestServiceEnums(String input) {
        ServiceEnum[] serviceEnums = ServiceEnum.values();
        List<ServiceEnum> closestEnums = new ArrayList<>();
        int minDistance = Integer.MAX_VALUE;

        for (ServiceEnum serviceEnum : serviceEnums) {
            int distance = calculateDamerauLevenshteinDistance(serviceEnum.getKeywords().toLowerCase(), input.toLowerCase());
            if (distance < minDistance) {
                closestEnums.clear();
                closestEnums.add(serviceEnum);
                minDistance = distance;
            } else if (distance == minDistance) {
                closestEnums.add(serviceEnum);
            }
        }

        return closestEnums;
    }


    private int calculateDamerauLevenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                int substitutionCost = s1.charAt(i - 1) == s2.charAt(j - 1) ? 0 : 1;
                dp[i][j] = Math.min(
                        Math.min(dp[i - 1][j] + 1, dp[i][j - 1] + 1),
                        dp[i - 1][j - 1] + substitutionCost
                );

                if (i > 1 && j > 1 && s1.charAt(i - 1) == s2.charAt(j - 2) && s1.charAt(i - 2) == s2.charAt(j - 1)) {
                    dp[i][j] = Math.min(dp[i][j], dp[i - 2][j - 2] + substitutionCost);
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
