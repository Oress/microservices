package org.ipan.orders.application.handlers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.common.dto.messaging.payment.PaymentCompletedMessage;
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
public class PaymentCompletedCommandHandler {
    private final OrderRepository orderRepository;

    @Transactional
    public void completePayment(PaymentCompletedMessage paymentCompletedMessage) {
        Order order = orderRepository.findById(paymentCompletedMessage.getOrderId())
                .orElseThrow(() -> new OrderDomainException("Could not find order with id: " + paymentCompletedMessage.getOrderId()));
        order.pay();
        log.info("Order is paid with id: {}", order.getId());
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
