package org.ipan.payment.application.events;

import org.ipan.payment.domain.event.PaymentCompletedEvent;
import org.ipan.payment.domain.event.PaymentEvent;
import org.ipan.payment.domain.event.PaymentFailedEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class PaymentProcessedEventPublisher implements ApplicationEventPublisherAware {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    public void publishPaymentCompletedEvent(PaymentCompletedEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }

    public void publishPaymentFailedEvent(PaymentFailedEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }

    public void publishPaymentEvent(PaymentEvent event) {
        this.applicationEventPublisher.publishEvent(event);
    }
}
