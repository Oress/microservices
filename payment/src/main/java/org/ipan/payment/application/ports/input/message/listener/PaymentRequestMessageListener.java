package org.ipan.payment.application.ports.input.message.listener;

import org.ipan.common.dto.messaging.order.OrderCreatedMessage;

public interface PaymentRequestMessageListener {
    void orderCreated(OrderCreatedMessage orderCreatedMessage);
}
