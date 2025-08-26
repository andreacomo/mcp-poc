package it.codingjam.mcp.client.service;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class ChatService {

    private final ChatClient chatClient;

    public ChatService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String question) {
        return chatClient.prompt()
                .user(question)
                .call()
                .content();
    }
}
