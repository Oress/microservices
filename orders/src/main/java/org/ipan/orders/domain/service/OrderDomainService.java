package org.ipan.orders.domain.service;

import org.ipan.orders.domain.event.OrderCanceledEvent;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.ipan.orders.domain.event.OrderPaidEvent;
import org.ipan.orders.domain.model.Order;

public interface OrderDomainService {
    OrderCreatedEvent validateAndInitiateOrder(Order order);
    OrderPaidEvent payOrder(Order order);
    void approveOrder(Order order);
    OrderCanceledEvent cancelOrderPayment(Order order);
    void cancelOrder(Order order);
}
