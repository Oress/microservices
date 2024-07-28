package org.ipan.orders.domain.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.orders.domain.event.OrderCanceledEvent;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.ipan.orders.domain.event.OrderPaidEvent;
import org.ipan.orders.domain.model.Order;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;

@Slf4j
@Component
@AllArgsConstructor
public class OrderDomainServiceImpl implements OrderDomainService {
    private final OrderPriceCalculator orderPriceCalculator;

    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order) {
        // TODO: validate product, customer
        orderPriceCalculator.calculateTotalPrice(order);
        order.validateOrder();
        order.initializeOrder();
        log.info("Validated and initiated order: {}", order);
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
