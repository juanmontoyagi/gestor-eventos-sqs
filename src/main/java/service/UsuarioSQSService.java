package service;

import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.model.MessageAttributeValue;
import com.amazonaws.services.sqs.model.SendMessageRequest;
import lombok.AllArgsConstructor;
import model.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UsuarioSQSService {

    private final AmazonSQS sqsClient;

    private String getQueueUrl() {
        return sqsClient.getQueueUrl("usuarios-sqs").getQueueUrl();
    }

    public String publishStandardQueueMessage(Integer delaySeconds, Usuario usuario) {
        Map<String, MessageAttributeValue> atributosMensaje = new HashMap<>();
        atributosMensaje.put("id",
                new MessageAttributeValue()
                        .withStringValue(String.valueOf(usuario.id()))
                        .withDataType("String")
        );
        atributosMensaje.put("nombre",
                new MessageAttributeValue()
                        .withStringValue(usuario.nombre())
                        .withDataType("String")
        );
        atributosMensaje.put("correo",
                new MessageAttributeValue()
                        .withStringValue(usuario.correo())
                        .withDataType("String")
        );

        atributosMensaje.put("ciudad",
                new MessageAttributeValue()
                        .withStringValue(String.valueOf(usuario.edad()))
                        .withDataType("String")
        );

        atributosMensaje.put("genero",
                new MessageAttributeValue()
                        .withStringValue(Optional.ofNullable(usuario.genero()).orElse(Boolean.FALSE).toString())
                        .withDataType("String")
        );

        SendMessageRequest sendMessageRequest = new SendMessageRequest()
                .withQueueUrl(this.getQueueUrl())
                .withMessageBody(usuario.nombre())
                .withDelaySeconds(delaySeconds)
                .withMessageAttributes(atributosMensaje);

        return sqsClient.sendMessage(sendMessageRequest).getMessageId();
    }
}
