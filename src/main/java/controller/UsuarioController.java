package controller;

import lombok.AllArgsConstructor;
import model.Usuario;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.UsuarioSQSService;

@AllArgsConstructor
@RestController
@RequestMapping("/usuario-sqs")
public class UsuarioController {

    UsuarioSQSService usuarioSQSService;

    @PostMapping("/aws")
    public Mono<String> postMessageQueue(@RequestBody Usuario usuario) {
        return Mono.just(usuarioSQSService.publishStandardQueueMessage(10, usuario));
    }
}