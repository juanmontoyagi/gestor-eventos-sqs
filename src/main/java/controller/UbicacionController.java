package controller;

import lombok.AllArgsConstructor;
import model.Ubicacion;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import service.UbicacionSQSService;

@AllArgsConstructor
@RestController
@RequestMapping("/ubicacionessqs")
public class UbicacionController {

    UbicacionSQSService ubicacionSQSService;

    @PostMapping("/aws")
    public Mono<String> postMessageQueue(@RequestBody Ubicacion ubicacion) {
        return Mono.just(ubicacionSQSService.publishStandardQueueMessage(10, ubicacion));
    }
}
