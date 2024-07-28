package org.ipan.orders.messaging;

import lombok.AllArgsConstructor;
import org.ipan.common.dto.messaging.order.OrderCreatedMessage;
import org.ipan.common.dto.messaging.payment.PaymentCompletedMessage;
import org.ipan.common.messaging.Messaging;
import org.ipan.orders.application.ports.input.service.OrderApplicationService;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class PaymentEventsRabbitListener {
    private final OrderApplicationService orderApplicationService;

    @RabbitListener(bindings = {
            @QueueBinding(
                    exchange = @Exchange(value = Messaging.Exchanges.PAYMENT),
                    value = @Queue(value = Messaging.Queues.ORDERS),
                    key = Messaging.RoutingKeys.PAYMENT_COMPLETED)
    })
    public void paymentCompletedEvent(PaymentCompletedMessage paymentCompletedMessage) {
        orderApplicationService.completePayment(paymentCompletedMessage);
    }
}
