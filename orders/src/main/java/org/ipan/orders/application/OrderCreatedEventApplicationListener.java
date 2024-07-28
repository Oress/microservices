package org.ipan.orders.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.orders.application.ports.output.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
@AllArgsConstructor
public class OrderCreatedEventApplicationListener {
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;

    @TransactionalEventListener
    void process(OrderCreatedEvent event) {
        this.orderCreatedPaymentRequestMessagePublisher.publish(event);
    }
}
