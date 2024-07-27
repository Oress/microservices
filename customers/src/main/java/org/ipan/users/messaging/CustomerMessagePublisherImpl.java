package org.ipan.users.messaging;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.ipan.users.application.ports.output.message.publisher.CustomerMessagePublisher;
import org.ipan.users.domain.event.CustomerCreatedEvent;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CustomerMessagePublisherImpl implements CustomerMessagePublisher {
    private final RabbitTemplate ampqTemplate;
    private final ObjectMapper objectMapper;

    @Override
    public void publish(CustomerCreatedEvent customerCreatedEvent) {
        try {
            byte[] bytes = objectMapper.writeValueAsBytes(customerCreatedEvent);
            Message message = new Message(bytes);
            ampqTemplate.send(message);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
