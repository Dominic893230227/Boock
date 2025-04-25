package com.boock.service;

import reactor.core.publisher.Mono;

public interface AiService {
    String getAIResponse(String prompt);
    Mono<String> getAIResponseAsync(String prompt);
}
