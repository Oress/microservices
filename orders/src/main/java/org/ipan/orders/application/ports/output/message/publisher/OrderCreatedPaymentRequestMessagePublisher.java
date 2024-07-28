package org.ipan.orders.application.ports.output.message.publisher;

import org.ipan.common.event.publisher.DomainEventPublisher;
import org.ipan.orders.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
