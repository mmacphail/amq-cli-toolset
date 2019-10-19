package eu.macphail.amqclitoolset.amq.control;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import javax.jms.BytesMessage;

@Slf4j
@Component
public class JmsClient {

    @Autowired
    private JmsTemplate jmsTemplate;

    public void sendTextMessage(String destination, String text) {
        jmsTemplate.send(destination, session -> session.createTextMessage(text));
        log.trace("Text message sent at " + destination);
    }

    public void sendBytesMessage(String destination, byte[] payload) {
        jmsTemplate.send(destination, session -> {
            BytesMessage message = session.createBytesMessage();
            message.writeBytes(payload);
            return message;
        });
        log.trace("Bytes message sent at " + destination);
    }
}
