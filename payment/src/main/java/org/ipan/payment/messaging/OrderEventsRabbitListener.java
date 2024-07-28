package org.ipan.payment.messaging;

import lombok.AllArgsConstructor;
import org.ipan.common.dto.messaging.order.OrderCreatedMessage;
import org.ipan.common.messaging.Messaging;
import org.ipan.payment.application.ports.input.message.listener.PaymentRequestMessageListener;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class OrderEventsRabbitListener {
    private final PaymentRequestMessageListener paymentRequestMessageListener;

    @RabbitListener(bindings = {
            @QueueBinding(
                    value = @Queue(value = Messaging.Queues.PAYMENT),
                    exchange = @Exchange(value = Messaging.Exchanges.ORDER),
                    key = Messaging.RoutingKeys.ORDER_CREATED)
    })
    public void listenOrderCreatedEvent(OrderCreatedMessage orderCreatedMessage) {
        paymentRequestMessageListener.orderCreated(orderCreatedMessage);
    }
}
