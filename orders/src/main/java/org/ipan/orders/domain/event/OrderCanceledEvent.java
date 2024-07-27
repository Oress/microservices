package org.ipan.orders.domain.event;

import org.ipan.orders.domain.model.Order;

import java.time.ZonedDateTime;

public class OrderCanceledEvent extends OrderEvent {
    public OrderCanceledEvent(Order order, ZonedDateTime createdAt) {
        super(order, createdAt);
    }
}
