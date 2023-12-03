package service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.AllArgsConstructor;
import model.Comentario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Service
public class ComentarioSQSService {

    private final AmazonSQS sqsClient;

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("comentario-sqs").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, Comentario comentario) {
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();
        atributosMensaje.put("id",
                new MessageAttributeValue()
                        .withStringValue(String.valueOf(comentario.id()))
                        .withDataType("String")
        );
        atributosMensaje.put("autor",
                new MessageAttributeValue()
                        .withStringValue(String.valueOf(comentario.autor()))
                        .withDataType("String")
        );
        atributosMensaje.put("evento",
                new MessageAttributeValue()
                        .withStringValue(String.valueOf(comentario.evento()))
                        .withDataType("String")
        );
        atributosMensaje.put("texto",
                new MessageAttributeValue()
                        .withStringValue(comentario.texto())
                        .withDataType("String")
        );
        atributosMensaje.put("fecha",
                new MessageAttributeValue()
                        .withStringValue(comentario.fecha())
                        .withDataType("String")
        );

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl())
                .withMessageBody(comentario.texto())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}
