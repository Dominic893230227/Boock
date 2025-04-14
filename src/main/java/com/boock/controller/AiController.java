package com.boock.controller;

import com.boock.service.AiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/ai")
public class AiController {
    private final AiService aiService;

    public AiController(AiService aiService) {
        this.aiService = aiService;
    }

    @GetMapping("/chat")
    public String getResponse(@RequestParam("message") String message) {
        System.out.println(message);
        return aiService.getAIResponse(message);
    }

    @GetMapping("/chat-async")
    public Mono<String> getResponseAsync(@RequestParam("message") String message) {
        return aiService.getAIResponseAsync(message);
    }
}
