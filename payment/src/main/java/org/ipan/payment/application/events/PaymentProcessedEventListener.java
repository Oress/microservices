package org.ipan.payment.application.events;

import lombok.AllArgsConstructor;
import org.ipan.payment.application.PaymentProcessedMessagePublisher;
import org.ipan.payment.domain.event.PaymentCompletedEvent;
import org.ipan.payment.domain.event.PaymentFailedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@AllArgsConstructor
public class PaymentProcessedEventListener {
    private final PaymentProcessedMessagePublisher paymentProcessedMessagePublisher;

    @TransactionalEventListener
    public void onPaymentCompleted(PaymentCompletedEvent event) {
        paymentProcessedMessagePublisher.publishPaymentCompleted(event);
    }

    @TransactionalEventListener
    public void onPaymentFailed(PaymentFailedEvent event) {
//        paymentProcessedMessagePublisher.publishPaymentCompleted(event);
    }
}
