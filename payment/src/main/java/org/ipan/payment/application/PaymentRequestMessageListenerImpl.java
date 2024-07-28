package org.ipan.payment.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.common.dto.messaging.order.OrderCreatedMessage;
import org.ipan.payment.application.mapper.PaymentMapper;
import org.ipan.payment.application.ports.input.message.listener.PaymentRequestMessageListener;
import org.ipan.payment.domain.event.PaymentEvent;
import org.ipan.payment.domain.model.CreditEntry;
import org.ipan.payment.domain.model.CreditHistory;
import org.ipan.payment.domain.model.Payment;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Slf4j
@Component
@AllArgsConstructor
public class PaymentRequestMessageListenerImpl implements PaymentRequestMessageListener {
    private final ProcessPaymentHandler processPaymentHandler;

    @Override
    public void orderCreated(OrderCreatedMessage orderCreatedMessage) {
        processPaymentHandler.processPayment(orderCreatedMessage);

//        orderOutboxHelper.saveOrderOutboxMessage(paymentDataMapper.paymentEventToOrderEventPayload(paymentEvent),
//                paymentEvent.getPayment().getPaymentStatus(),
//                OutboxStatus.STARTED,
//                UUID.fromString(paymentRequest.getSagaId()));

    }

}
