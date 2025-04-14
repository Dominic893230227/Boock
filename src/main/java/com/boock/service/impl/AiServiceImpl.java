package com.boock.service.impl;

import com.boock.service.AiService;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AiServiceImpl implements AiService {

    private final ChatClient chatClient;

    // 通过构造器注入ChatClient
    public AiServiceImpl(ChatClient.Builder chatClientBuilder) {
        this.chatClient = chatClientBuilder.build();
    }
    @Override
    public String getAIResponse(String prompt) {
        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }

    @Override
    public Mono<String> getAIResponseAsync(String prompt) {
        return Mono.fromCallable(() -> chatClient.prompt()
                .user(prompt)
                .call()
                .content());
    }
}
