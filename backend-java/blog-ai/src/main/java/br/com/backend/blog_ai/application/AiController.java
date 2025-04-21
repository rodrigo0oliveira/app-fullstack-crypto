package br.com.backend.blog_ai.application;

import br.com.backend.blog_ai.model.MessageDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/ai")
@Slf4j
public class AiController {

    ChatClient chatClient;

    public AiController(ChatClient.Builder builder) {
        this.chatClient = builder.build();
    }

    @PostMapping()
    public String openAiIntegration(@RequestBody MessageDto messageDto) {
        return chatClient.prompt()
                .user(messageDto.message())
                .call()
                .content();
    }


}
