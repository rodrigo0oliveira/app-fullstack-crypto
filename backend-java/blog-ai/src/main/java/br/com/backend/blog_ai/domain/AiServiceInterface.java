package br.com.backend.blog_ai.domain;

import br.com.backend.blog_ai.model.MessageDto;

public interface AiServiceInterface {

    public MessageDto sendRequestToAi(String message);
}
