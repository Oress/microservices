package org.ipan.payment.application;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ipan.common.dto.messaging.order.OrderCreatedMessage;
import org.ipan.common.dto.messaging.payment.PaymentCompletedMessage;
import org.ipan.common.messaging.Messaging;
import org.ipan.payment.application.mapper.PaymentMapper;
import org.ipan.payment.domain.event.PaymentCompletedEvent;
import org.ipan.payment.domain.event.PaymentEvent;
import org.ipan.payment.domain.event.PaymentFailedEvent;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;


@Slf4j
@AllArgsConstructor
@Component
public class PaymentProcessedMessagePublisher {
    private final RabbitTemplate rabbitTemplate;
    private final PaymentMapper paymentMapper;

    public void publishPaymentCompleted(PaymentCompletedEvent event) {
        log.info("Publishing PaymentCompletedEvent to RabbitMQ: {}", event);

        PaymentCompletedMessage payload = paymentMapper.paymentCompletedEventToPaymentCompletedMessage(event);
        this.rabbitTemplate.convertAndSend(Messaging.Exchanges.PAYMENT,
                Messaging.RoutingKeys.PAYMENT_COMPLETED,
                payload);
    }
}
