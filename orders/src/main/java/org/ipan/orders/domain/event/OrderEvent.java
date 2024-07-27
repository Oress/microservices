package org.ipan.orders.domain.event;

import lombok.Data;
import org.ipan.common.domain.event.DomainEvent;
import org.ipan.orders.domain.model.Order;

import java.time.ZonedDateTime;

@Data
public abstract class OrderEvent implements DomainEvent<Order> {
    private final Order order;
    private final ZonedDateTime createdAt;
}
