package br.com.backend.blog_ai.application;

import br.com.backend.blog_ai.domain.AiServiceInterface;
import br.com.backend.blog_ai.model.MessageDto;
import org.springframework.stereotype.Service;

@Service
public class AiService{

    private final AiServiceInterface aiServiceInterface;

    public AiService(AiServiceInterface aiServiceInterface){
        this.aiServiceInterface = aiServiceInterface;
    }

    public MessageDto sendRequestToAi(String message) {
         return this.aiServiceInterface.sendRequestToAi(message);
    }

}
