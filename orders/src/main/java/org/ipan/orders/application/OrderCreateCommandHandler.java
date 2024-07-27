package org.ipan.orders.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.orders.application.model.create.CreateOrderCommand;
import org.ipan.orders.application.model.create.CreateOrderResponse;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.ipan.orders.domain.model.Order;
import org.ipan.orders.domain.service.OrderDomainService;
import org.ipan.orders.mapper.OrderMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateCommandHandler {
    private final OrderDomainService orderDomainService;
    private final OrderMapper orderDataMapper;
    private final OrderSagaHelper orderSagaHelper;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
        checkCustomer(createOrderCommand.getCustomerId());
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order);
        saveOrder(order);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        return orderCreatedEvent;
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId().getValue());
        CreateOrderResponse createOrderResponse = orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder(),
                "Order created successfully");

//        paymentOutboxHelper.savePaymentOutboxMessage(orderDataMapper
//                .orderCreatedEventToOrderPaymentEventPayload(orderCreatedEvent),
//                orderCreatedEvent.getOrder().getOrderStatus(),
//                orderSagaHelper.orderStatusToSagaStatus(orderCreatedEvent.getOrder().getOrderStatus()),
//                OutboxStatus.STARTED,
//                UUID.randomUUID());

        log.info("Returning CreateOrderResponse with order id: {}", orderCreatedEvent.getOrder().getId());

        return createOrderResponse;
    }
}
