package controller;

import lombok.AllArgsConstructor;
import model.Evento;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.EventoSQSService;

@AllArgsConstructor
@RestController
@RequestMapping("/evento-sqs")
public class EventoController {

    EventoSQSService eventoSQSService;

    @PostMapping("/aws")
    public Mono<String> postMessageQueue(@RequestBody Evento evento) {
        return Mono.just(eventoSQSService.publishStandardQueueMessage(10, evento));
    }
}
