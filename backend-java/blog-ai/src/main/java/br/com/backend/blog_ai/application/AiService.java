package br.com.backend.blog_ai.application;

import br.com.backend.blog_ai.model.MessageDto;
import lombok.RequiredArgsConstructor;

import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.vertexai.gemini.VertexAiGeminiChatModel;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AiService {

    private final VertexAiGeminiChatModel geminiChatModel;

    public MessageDto sendMessageToGemini(String message) {
        Prompt prompt = new Prompt("Responda apenas perguntas relacionadas a criptomoedas ou programação, os textos devem ser de forma bem resumida e de ate 5 linhas e sem letras em negrito" +
                "ou de outro formato. Caso a pergunta esteja fora desses temas" +
                ", responda com: Só posso responder perguntas relacionadas a criptomoedas e programação." +
                message);

        String messageDto = geminiChatModel.call(prompt.getContents());

        return new MessageDto(messageDto);
    }




}
