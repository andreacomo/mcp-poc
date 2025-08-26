package it.codingjam.mcp.client.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.tool.ToolCallbackProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ChatConfig {

    @Bean
    ChatClient chat(ChatClient.Builder chatClientBuilder, ToolCallbackProvider tools) {
        return chatClientBuilder
                .defaultToolCallbacks(tools)
                .defaultSystem("You are an expert in weather and forecast in Italy")
                .build();
    }
}
