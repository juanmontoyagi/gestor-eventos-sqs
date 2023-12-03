package service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.AllArgsConstructor;
import model.Evento;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class EventoSQSService {

    private final AmazonSQS sqsClient;

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("eventos-sqs").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, Evento evento) {
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();
        atributosMensaje.put("id",
                new MessageAttributeValue()
                        .withStringValue(String.valueOf(evento.id()))
                        .withDataType("String")
        );
        atributosMensaje.put("titulo",
                new MessageAttributeValue()
                        .withStringValue(evento.titulo())
                        .withDataType("String")
        );
        atributosMensaje.put("descripcion",
                new MessageAttributeValue()
                        .withStringValue(evento.descripcion())
                        .withDataType("String")
        );
        atributosMensaje.put("fecha",
                new MessageAttributeValue()
                        .withStringValue(evento.fecha())
                        .withDataType("String")
        );
        atributosMensaje.put("capacidad",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(evento.capacidad()).orElse(0).toString())
                        .withDataType("String")
        );

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl())
                .withMessageBody(evento.titulo())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}
