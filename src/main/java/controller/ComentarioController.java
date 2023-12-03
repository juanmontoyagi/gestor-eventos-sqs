package controller;

import lombok.AllArgsConstructor;
import model.Comentario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.ComentarioSQSService;

@AllArgsConstructor
@RestController
@RequestMapping("/comentario-sqs")
public class ComentarioController {

    ComentarioSQSService comentarioSQSService;

    @PostMapping("/aws")
    public Mono<String> postMessageQueue(@RequestBody Comentario comentario) {
        return Mono.just(comentarioSQSService.publishStandardQueueMessage(10, comentario));
    }
}
