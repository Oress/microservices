package org.ipan.orders.application;

import lombok.extern.slf4j.Slf4j;
import org.ipan.common.event.publisher.DomainEventPublisher;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent> {
    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(OrderCreatedEvent event) {
        this.applicationEventPublisher.publishEvent(event);
        log.info("Published event: {}", event);
    }
}
