package fr.fms.services;

import org.springframework.ai.chat.ChatClient;
import org.springframework.stereotype.Service;

@Service
public class AiService {
    private final ChatClient chatClient;

    public AiService(ChatClient chatClient) {
        this.chatClient = chatClient;
    }

    public String chat(String prompt) {
        return chatClient.call(prompt);
    }    
}
