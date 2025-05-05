package br.com.backend.blog_ai.application;

import br.com.backend.blog_ai.model.MessageDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/ai")
@Slf4j
@RequiredArgsConstructor
public class AiController {

    private final AiService aiService;

    @PostMapping()
    public ResponseEntity<MessageDto> askAi(@RequestBody String message){
        MessageDto messageDto = aiService.sendRequestToAi(message);

        return ResponseEntity.ok(messageDto);
    }


}
