package org.ipan.common.event.publisher;

import org.ipan.common.domain.event.DomainEvent;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T event);
}
