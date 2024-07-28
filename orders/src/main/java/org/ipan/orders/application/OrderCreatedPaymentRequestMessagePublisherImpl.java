package org.ipan.orders.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.common.messaging.Messaging;
import org.ipan.orders.application.model.create.messaging.OrderCreatedMessage;
import org.ipan.orders.application.ports.output.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.ipan.orders.mapper.OrderMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@AllArgsConstructor
public class OrderCreatedPaymentRequestMessagePublisherImpl implements OrderCreatedPaymentRequestMessagePublisher {
    private RabbitTemplate rabbitTemplate;
    private OrderMapper orderMapper;

    @Override
    public void publish(OrderCreatedEvent event) {
        log.info("Publishing OrderCreatedEvent to RabbitMQ: {}", event);

        OrderCreatedMessage payload = orderMapper.orderCreatedEventToOrderCreatedMessage(event);
        this.rabbitTemplate.convertAndSend(Messaging.Exchanges.ORDER,
                Messaging.RoutingKeys.ORDER_CREATED,
                payload);
    }
}
