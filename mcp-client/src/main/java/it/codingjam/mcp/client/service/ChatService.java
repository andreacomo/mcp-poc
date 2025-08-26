package it.codingjam.mcp.client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public Flux<String> chat(String question) {
        return chatClient.prompt()
                .user(question)
                .stream()
                .content();
    }
}
