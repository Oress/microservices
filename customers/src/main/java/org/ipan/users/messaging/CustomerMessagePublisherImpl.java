package org.ipan.users.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.users.application.ports.output.message.publisher.CustomerMessagePublisher;
import org.ipan.users.domain.event.CustomerCreatedEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@AllArgsConstructor
@Slf4j
public class CustomerMessagePublisherImpl implements CustomerMessagePublisher {
    private final RabbitTemplate ampqTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(CustomerCreatedEvent customerCreatedEvent) {
        try {
            log.info("Sending message to RabbitMQ....");
            byte[] bytes = objectMapper.writeValueAsBytes(customerCreatedEvent);
            Message message = new Message(bytes);
            CorrelationData cd = new CorrelationData(UUID.randomUUID().toString());
            ampqTemplate.convertAndSend("", message, cd);
            cd.getFuture().thenAcceptAsync(confirm -> {
                // It doesn't work
                if (confirm.isAck()){
                    log.info("Message sent successfully");
                } else {
                    log.error("Message not sent");
                }
            }).exceptionally(e -> {
                log.error("Message not sent");
                return null;
            });
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
