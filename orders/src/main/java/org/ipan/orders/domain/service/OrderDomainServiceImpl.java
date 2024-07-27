package org.ipan.orders.domain.service;

import lombok.extern.slf4j.Slf4j;
import org.ipan.orders.domain.event.OrderCanceledEvent;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.ipan.orders.domain.event.OrderPaidEvent;
import org.ipan.orders.domain.model.Order;

import java.time.ZonedDateTime;

@Slf4j
public class OrderDomainServiceImpl implements OrderDomainService {
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order) {
        log.info("Validating and initiating order: {}", order);

        // TODO: validate product, customer
        order.validateOrder();
        order.initializeOrder();
        return new OrderCreatedEvent(order, ZonedDateTime.now());
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        log.info("Paying order: {}", order);
        order.pay();
        return new OrderPaidEvent(order, ZonedDateTime.now());
    }

    @Override
    public void approveOrder(Order order) {
        log.info("Approving order: {}", order);
        order.approve();
    }

    @Override
    public OrderCanceledEvent cancelOrderPayment(Order order) {
        log.info("Cancelling order payment: {}", order);
        order.cancel();
        return new OrderCanceledEvent(order, ZonedDateTime.now());
    }

    @Override
    public void cancelOrder(Order order) {
        log.info("Cancellation confirmed order: {}", order);
        order.confirmCancellation();
    }
}
