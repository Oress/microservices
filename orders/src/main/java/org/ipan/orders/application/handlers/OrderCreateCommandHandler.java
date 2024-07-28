package org.ipan.orders.application.handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.orders.application.model.create.CreateOrderCommand;
import org.ipan.orders.application.model.create.CreateOrderResponse;
import org.ipan.orders.application.ports.output.message.publisher.OrderCreatedPaymentRequestMessagePublisher;
import org.ipan.orders.domain.event.OrderCreatedEvent;
import org.ipan.orders.domain.exception.OrderDomainException;
import org.ipan.orders.domain.model.Order;
import org.ipan.orders.domain.repository.OrderRepository;
import org.ipan.orders.domain.service.OrderDomainService;
import org.ipan.orders.mapper.OrderMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Component
@AllArgsConstructor
public class OrderCreateCommandHandler {
    private final OrderDomainService orderDomainService;
    private final OrderMapper orderDataMapper;
    private final OrderRepository orderRepository;
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;
    // option 2
//    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;
//    private final OrderSagaHelper orderSagaHelper;

    @Transactional
    public CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand) {
//        checkCustomer(createOrderCommand.getCustomerId());
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order);
        saveOrder(order);
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
//        applicationDomainEventPublisher.publish(orderCreatedEvent);
        log.info("Order is created with id: {}", orderCreatedEvent.getOrder().getId());
        return orderDataMapper.orderCreatedEventToOrderCreatedResponse(orderCreatedEvent);
    }

    private Order saveOrder(Order order) {
        Order orderResult = orderRepository.save(order);
        if (orderResult == null) {
            log.error("Could not save order!");
            throw new OrderDomainException("Could not save order!");
        }
        log.info("Order is saved with id: {}", orderResult.getId());
        return orderResult;
    }
}
