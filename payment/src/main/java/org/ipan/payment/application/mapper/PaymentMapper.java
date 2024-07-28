package org.ipan.payment.application.mapper;

import org.ipan.common.dto.messaging.order.OrderCreatedMessage;
import org.ipan.common.dto.messaging.payment.PaymentCompletedMessage;
import org.ipan.payment.domain.event.PaymentCompletedEvent;
import org.ipan.payment.domain.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {
    public Payment paymentRequestModelToPayment(OrderCreatedMessage orderCreatedMessage) {
        return Payment.builder()
                .customerId(orderCreatedMessage.getCustomerId())
                .orderId(orderCreatedMessage.getOrderId())
                .price(orderCreatedMessage.getTotalPrice())
                .build();
    }

    public PaymentCompletedMessage paymentCompletedEventToPaymentCompletedMessage(PaymentCompletedEvent event) {
        return PaymentCompletedMessage.builder()
                .orderId(event.getPayment().getOrderId())
                .build();
    }
}
