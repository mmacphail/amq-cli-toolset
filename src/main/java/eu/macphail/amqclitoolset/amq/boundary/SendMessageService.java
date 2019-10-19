package eu.macphail.amqclitoolset.amq.boundary;

import eu.macphail.amqclitoolset.amq.control.JmsClient;
import eu.macphail.amqclitoolset.cli.entity.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@Component
public class SendMessageService {

    @Autowired
    JmsClient client;

    public void sendMessage(String destination, MessageType messageType, String messagePayloadPath) throws IOException {
        log.info("Sending a " + messageType.getLabel() + " message to " + destination + " from file " + messagePayloadPath);
        byte[] payload = Files.readAllBytes(Paths.get(messagePayloadPath));
        if(messageType == MessageType.TEXT) {
            client.sendTextMessage(destination, new String(payload, StandardCharsets.UTF_8));
        } else {
            client.sendBytesMessage(destination, payload);
        }
        log.info("Message was successfully sent");
    }
}
