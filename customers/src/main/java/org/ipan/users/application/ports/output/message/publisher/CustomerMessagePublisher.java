package org.ipan.users.application.ports.output.message.publisher;

import org.ipan.users.domain.event.CustomerCreatedEvent;

public interface CustomerMessagePublisher {
    void publish(CustomerCreatedEvent customerCreatedEvent);
}